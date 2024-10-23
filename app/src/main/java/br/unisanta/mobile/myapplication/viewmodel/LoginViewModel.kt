package br.unisanta.mobile.myapplication.viewmodel

import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    companion object{
        private val users = mutableListOf<User>()
    }
    fun logar(login:String,senha:String):Int{
        val existe = users.find { it.login ==login && it.senha==senha }

        if (existe!= null){
            if(existe.isAdmin) return 2

            return 1
        }else{
            return 0
        }

    }
    fun cadastrar(user:User):String{
        users.add(user)
        return "Cadastrado"
    }
    fun getUsers(): List<User>{
        return users.filter { !it.isAdmin }
    }
    fun getLoggedUser(login:String,senha:String): User?{
        return users.find { it.login == login && it.senha == senha }
    }
    fun changeUser(login:String, senha: String, newNome:String,newEmail:String,newUrl:String):String{
        val user = users.find { it.login == login && it.senha == senha }
        return if (user != null || newNome!="" || newEmail != "" || newUrl != "") {
            user?.nome = newNome
            user?.email = newEmail
            user?.profilePic = newUrl
            "Usuario alterado com sucesso."
        } else {
            "Erro ao Alterar o Usuario."
        }
    }
}