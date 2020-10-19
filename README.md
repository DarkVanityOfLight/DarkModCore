# RecraftedCore
The Core Plugin, for all following Minecraft Plugins from me


## License:
```
You are allowed to change the source code to your needs, there is no warranty
that this plugin and all dependents work. If you want to use the plugin or any modified versions of it
on a public server please contact me beforhand, make sure to give proper credits bla bla.
Have fun ;D
```

## Usage:

This Plugin is entirely written in Kotlin, so there may be some logs of diffrent plugins loading classes
from other plugins standard library, ignore those warnings, I try to fix it but I can't guarantee this will work

For the main class do:
```
class MyPlugin : ARecraftedPlugin {

      override fun onEnable() {
        super.onEnable()
        info("[MyPlugin] Enabling MyPlugin")
    }
}
```



