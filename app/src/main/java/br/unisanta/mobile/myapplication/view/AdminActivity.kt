package br.unisanta.mobile.myapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.unisanta.mobile.myapplication.databinding.ActivityAdminBinding
import br.unisanta.mobile.myapplication.viewmodel.LoginViewModel
import br.unisanta.mobile.myapplication.viewmodel.UserAdapter

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding
    private val viewmodel = LoginViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val users = viewmodel.getUsers()
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = UserAdapter(users)

    }
}
