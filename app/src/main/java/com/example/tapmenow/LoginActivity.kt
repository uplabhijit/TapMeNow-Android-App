package com.example.tapmenow

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.dialog_forgot_password.view.*
import java.util.*


class LoginActivity : AppCompatActivity() {
    internal lateinit var rl_login_layout: RelativeLayout
    internal lateinit var rl_Signup_layout: RelativeLayout
    internal lateinit var buttonFacebookLogin: RelativeLayout
    private val RC_SIGN_IN = 9001
    private lateinit var auth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mcallbackManager: CallbackManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initUiElements()
        onTap()


        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)


        // Initialize Facebook Login button
        mcallbackManager = CallbackManager.Factory.create()


    }

    //get an access token for the signed-in user,
    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("FacebookAccessToken", "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("success", "signInWithCredential:success")
                    Toast.makeText(
                        baseContext, "Authentication Success.",
                        Toast.LENGTH_SHORT
                    ).show()
                    val user = auth.currentUser
                    updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("failure", "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    /*  updateUI(null)*/
                }

                // ...
            }
    }

    //to Bind UIElements with view
    private fun initUiElements() {
        var txt_or_joinwith = findViewById(R.id.txt_or_joinwith) as TextView
        rl_login_layout = findViewById(R.id.rl_login_layout) as RelativeLayout
        rl_Signup_layout = findViewById(R.id.rl_Signup_layout) as RelativeLayout
        rl_Signup_layout = findViewById(R.id.rl_Signup_layout) as RelativeLayout
        buttonFacebookLogin = findViewById(R.id.loginWithFbBtn) as RelativeLayout
        val sourceString = " Or <b>Join With</b>"
        txt_or_joinwith.text = Html.fromHtml(sourceString)


    }

    //Func to handle onClick functions
    private fun onTap() {
        signup_Tab.setOnClickListener {
            signup_Tab.setTextColor(ContextCompat.getColor(this@LoginActivity, R.color.black))
            login_Tab.setTextColor(ContextCompat.getColor(this@LoginActivity, R.color.grey))
            rl_login_layout.visibility = View.GONE
            rl_Signup_layout.visibility = View.VISIBLE


        }
        login_Tab.setOnClickListener {
            login_Tab.setTextColor(ContextCompat.getColor(this@LoginActivity, R.color.black))
            signup_Tab.setTextColor(ContextCompat.getColor(this@LoginActivity, R.color.grey))
            rl_Signup_layout.visibility = View.GONE
            rl_login_layout.visibility = View.VISIBLE

        }
        forgotPasswordButton.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_forgot_password, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)

            //show dialog
            val mAlertDialog = mBuilder.show()
            //login button click of custom layout
            mDialogView.submitButton.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
                //get text from EditTexts of custom layout

                //set the input text in TextView

            }
            mDialogView.cancelbtn.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
                println("CANCEL")
                //get text from EditTexts of custom layout

                //set the input text in TextView

            }


        }
        buttonFacebookLogin.setOnClickListener {
            LoginManager.getInstance()
                .logInWithReadPermissions(this@LoginActivity, Arrays.asList("email", "public_profile", "user_friends"))
            LoginManager.getInstance().registerCallback(mcallbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    Log.d("SUCCESS>>>>>>", "facebook:onSuccess:$loginResult")
                    handleFacebookAccessToken(loginResult.accessToken)
                }

                override fun onCancel() {
                    Log.d("OnCancel>>>>>", "facebook:onCancel")
                    // ...
                }

                override fun onError(error: FacebookException) {
                    Log.d("OnError>>>>>>>>>", "facebook:onError", error)
                    // ...
                }
            })
        }


        loginWithGoogleBtn.setOnClickListener {

            signIn()
        }

    }

    //google signin method
    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            updateUI(currentUser)
        } else {
            updateUI(null)
        }

    }

    //Update the Ui depending on User Loging Status
    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            println(user.email)
            println(user.displayName)
            println(user.photoUrl)

            val intent = Intent(this@LoginActivity, DashboardActivity::class.java);
            intent.putExtra("user", user)
            startActivity(intent);
            finish()

        } else {
            // textSignInStatus.text = "Error: sign in failed"
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d("firebaseAuthWithGoogle", "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("LOGIN STATUS>>>", "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("LOGIN STATUS>>>", "signInWithCredential:failure", task.exception)
                    //Snackbar.make(main_layout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()

                }

                // ...
            }
    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("Google sign in failed", "Google sign in failed", e)
                // ...
            }
        } else {
            mcallbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }


}