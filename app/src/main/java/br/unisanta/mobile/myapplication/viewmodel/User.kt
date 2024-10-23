package br.unisanta.mobile.myapplication.viewmodel

import java.io.Serializable

data class User(
    val login:String,
    val senha:String,
    var nome:String,
    var email:String,
    var profilePic:String,
    val isAdmin:Boolean
):Serializable
