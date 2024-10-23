package br.unisanta.mobile.myapplication.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.unisanta.mobile.myapplication.R
import br.unisanta.mobile.myapplication.databinding.ActivityMainBinding
import br.unisanta.mobile.myapplication.viewmodel.LoginViewModel
import br.unisanta.mobile.myapplication.viewmodel.User

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val viewmodel = LoginViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val admCriado = viewmodel.cadastrar(
            User(
                "Admin",
                "123456",
                "Admin",
                "admin@gmail.com",
                "dontNeed",
                true
            )
        )

        binding.btnEntrar.setOnClickListener {
            val login = binding.edtLogin.text.trim().toString()
            val senha = binding.edtSenha.text.trim().toString()
            if(viewmodel.logar(login,senha) == 2){
                val intentAdm = Intent(this, AdminActivity::class.java)
                startActivity(intentAdm)
            }
            if(viewmodel.logar(login,senha) == 1){
                val msg = getString(R.string.usuario_logado)
                val intentUser = Intent(this, UserActivity::class.java)
                val userLogged = viewmodel.getLoggedUser(login,senha)
                intentUser.putExtra("loggedUser",userLogged)
                startActivity(intentUser)
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
            if(viewmodel.logar(login,senha) == 0){
                val msg = getString(R.string.usuario_not_found)
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
            clearFields()
        }
        binding.btnCadastrar.setOnClickListener {
            val login = binding.edtLogin.text.trim().toString()
            val senha = binding.edtSenha.text.trim().toString()
            val nome = binding.edtNome.text.trim().toString()
            val email = binding.edtEmail.text.trim().toString()
            val urlPic = binding.edtUrlImg.text.trim().toString()

            val msg = viewmodel.cadastrar(User(login,senha,nome,email,urlPic,false))
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

            clearFields()
        }
    }
    private fun clearFields(){
        binding.edtLogin.text.clear()
        binding.edtSenha.text.clear()
        binding.edtNome.text.clear()
        binding.edtEmail.text.clear()
        binding.edtUrlImg.text.clear()
    }
}