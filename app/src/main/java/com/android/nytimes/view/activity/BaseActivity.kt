package com.android.nytimes.view.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.android.nytimes.R
import com.android.nytimes.presenter.ipresenter.IPresenter
import com.android.nytimes.util.CodeSnippet
import com.android.nytimes.util.CustomException
import com.android.nytimes.util.InternetConnectionObserver
import com.android.nytimes.view.iview.IView
import com.android.nytimes.view.widgets.CustomProgressBar
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

import java.util.*


abstract class BaseActivity<B : ViewDataBinding, T : IPresenter> : AppCompatActivity(), IView {

    protected var TAG = javaClass.simpleName
    protected var mParentView: View? = null
    protected var mCodeSnippet: CodeSnippet? = null
    protected var iPresenter: T? = null
    protected var bViewDataBinding: B? = null
    private var mCustomProgressbar: CustomProgressBar? = null
    private var isFirstTime = true

    private lateinit var forceLogoutDialog: AlertDialog /*SweetAlertDialog*/


    private var calender: Calendar? = null

    private val progressBar: CustomProgressBar
        get() {
            if (mCustomProgressbar == null) {
                mCustomProgressbar = CustomProgressBar(this)
            }
            return mCustomProgressbar!!
        }

    override fun getActivity(): FragmentActivity {
        return this@BaseActivity
    }

    override val codeSnippet: CodeSnippet
        get() {
            if (mCodeSnippet == null) {
                mCodeSnippet = CodeSnippet(this.getActivity())
                return mCodeSnippet!!
            }
            return mCodeSnippet as CodeSnippet
        }


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        iPresenter = onInitializePresenter()
        iPresenter!!.onCreate(intent.extras)
        if (iPresenter != null) {
            onPresenterCreated()
        }

        val connectionLiveData = InternetConnectionObserver(this)
        connectionLiveData.observe(this, androidx.lifecycle.Observer { it ->
            if (!isFirstTime)
                onConnectionChange(it)
            isFirstTime = false
        })
    }

    override fun onPresenterCreated() {

    }

    abstract fun getLayoutId(): Int


    abstract fun onInitializePresenter(): T


    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        mParentView = window.decorView.findViewById(android.R.id.content)
        return super.onCreateView(name, context, attrs)
    }


    override fun onStart() {
        super.onStart()
        if (iPresenter != null) iPresenter!!.onStart()
    }

    override fun onStop() {
        super.onStop()
        if (iPresenter != null) iPresenter!!.onStop()
    }

    override fun onPause() {
        super.onPause()
        if (iPresenter != null) iPresenter!!.onPause()
    }

    override fun onResume() {
        super.onResume()
        isFirstTime = true
        if (iPresenter != null) iPresenter!!.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (iPresenter != null) iPresenter!!.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (iPresenter != null) {
            iPresenter?.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun showMessage(message: String?) {
        if (!message.isNullOrBlank())
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(resId: Int) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(e: CustomException) {
        Timber.d("showMessage: ${e.exception}")
    }

    override fun showAlerterMessage(message: String?) {
        /* Alerter.create(this)
                 .setText(message)
                 .setBackgroundDrawable(null)
                 .setBackgroundColorRes(R.color.white)
                 .setTextColor(R.color.colorAccent)
                 .setDuration(1000)
                 .hideIcon()
                 .show()
         }*/
    }

    override fun showAlerterMessage(resId: Int) {

        /*Alerter.create(this)
            .setText(getString(resId))
            .setBackgroundDrawable(null)
            .setBackgroundColorRes(R.color.white)
            .setTextColor(R.color.colorAccent)
            .setDuration(1000)
            .hideIcon()
            .show()*/

    }

    override fun showAlerterMessage(e: CustomException) {

        /* Alerter.create(this)
             .setText(e.exception)
             .setBackgroundDrawable(null)
             .setBackgroundColorRes(R.color.white)
             .setTextColor(R.color.colorAccent)
             .setDuration(1000)
             .hideIcon()
             .show()*/

    }

    override fun showForceLogoutDialog() {
        /*  forceLogoutDialog = SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
          forceLogoutDialog.setTitleText(resources.getString(R.string.un_authorized_logout))
              .setContentText(resources.getString(R.string.un_authorized_logout_desc))
              .setConfirmText(resources.getString(R.string.dialog_ok))
              .showCancelButton(false)
              .setCustomImage(R.drawable.ic_warning)
              .setConfirmClickListener {
                  val logoutIntent = Intent(this@BaseActivity, LoginAgentActivity::class.java)
                  logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                  logoutIntent.putExtra(Constants.BundleKey.CLEAR_SHARED_PREFERENCES, false)
                  forceLogoutDialog.dismiss()
                  startActivity(logoutIntent)
                  finish()
              }
              .show()*/
    }


    override fun showProgressbar() {
        if (!isFinishing)
            progressBar.show()
    }

    override fun dismissProgressbar() {
        runOnUiThread {
            try {
                progressBar.dismissProgress()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun showSnackBar(message: String) {
        if (mParentView != null) {
            val snackbar = Snackbar.make(mParentView!!, message, Snackbar.LENGTH_LONG)
            snackbar.setActionTextColor(Color.GREEN)
            snackbar.show()
        }
    }

    override fun showSnackBar(view: View, message: String) {
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snackBar.setActionTextColor(Color.RED)
        snackBar.show()
    }


    override fun showNetworkMessage() {
        if (mParentView != null) {
            val snackBar =
                Snackbar.make(mParentView!!, resources.getString(R.string.label_no_network), Snackbar.LENGTH_LONG)
            snackBar.setActionTextColor(Color.RED)
            snackBar.setAction(R.string.action_settings) { mCodeSnippet!!.showNetworkSettings() }
            snackBar.show()
        }
    }


    override fun onBackPress() {
        onBackPressed()
    }

    fun replaceFragment(layoutId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(layoutId, fragment).commit()
    }


}
