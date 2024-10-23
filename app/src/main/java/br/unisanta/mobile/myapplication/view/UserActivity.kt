package br.unisanta.mobile.myapplication.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.unisanta.mobile.myapplication.R
import br.unisanta.mobile.myapplication.databinding.ActivityUserBinding
import br.unisanta.mobile.myapplication.viewmodel.LoginViewModel
import br.unisanta.mobile.myapplication.viewmodel.User
import coil.load

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private val viewmodel = LoginViewModel()
    private val defaultUrl = "https://media.istockphoto.com/id/1409329028/vector/no-picture-available-placeholder-thumbnail-icon-illustration-design.jpg?s=612x612&w=0&k=20&c=_zOuJu755g2eEUioiOUdz_mHKJQJn-tDgIAhQzyeKUQ="
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var loggedUser: User? = intent.getSerializableExtra("loggedUser") as? User
        if(loggedUser != null){
            binding.edtvNome.setText(loggedUser.nome)
            binding.edtvEmail.setText(loggedUser.email)
            binding.userImg.load(loggedUser.profilePic ?: defaultUrl)

            binding.btnAlterar.isEnabled = true
        }
        else
        {
            binding.edtvNome.setText(getString(R.string.usuario_not_found))
            binding.edtvEmail.setText(getString(R.string.email_not_found))
            binding.userImg.load(defaultUrl)
            binding.btnAlterar.isEnabled = false
        }

        binding.btnAlterar.setOnClickListener {
            val newNome = binding.edtvNome.text.trim().toString()
            val newEmail = binding.edtvEmail.text.trim().toString()
            val newUrl = binding.edtvUrl.text.trim().toString()
            if(loggedUser != null) {
                val msg = viewmodel.changeUser(loggedUser!!.login, loggedUser!!.senha, newNome, newEmail, newUrl )
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

                loggedUser = viewmodel.getLoggedUser(loggedUser!!.login,loggedUser!!.senha)
                binding.edtvNome.setText(loggedUser!!.nome)
                binding.edtvEmail.setText(loggedUser!!.email)
                binding.userImg.load(loggedUser!!.profilePic ?: defaultUrl)
            }

        }
        binding.fab.setOnClickListener { finish() }
    }
}