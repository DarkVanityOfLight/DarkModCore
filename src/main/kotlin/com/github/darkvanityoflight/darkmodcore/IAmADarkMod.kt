package com.github.darkvanityoflight.darkmodcore



interface IAmADarkMod {


    fun debug(message : String?)
    fun debug(message: String?, vararg vars: Any?)

    fun severe(message: String?)
    fun severe(message: String?, error : Throwable?)

    fun warning(message: String?)
    fun warning(message: String?, error: Throwable?)
    fun warning(message: String?, vararg vars: Any?)

    fun info(message: String?)
    fun info(message: String?, vararg vars: Any?)


}