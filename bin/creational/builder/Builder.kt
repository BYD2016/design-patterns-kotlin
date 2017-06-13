package creational.builder

/**
 The builder pattern is used to create complex objects with
 constituent parts that must be created in the same order or
 using a specific algorithm. An external class controls the
 construction algorithm.
 */

import java.io.File

// Let's assume that Dialog class is provided by external library.
// We have only access to Dialog public interface which cannot be changed.

class Dialog() {

    fun showTitle() = println("showing title")

    fun setTitle(text: String) = println("setting title text $text")

    fun setTitleColor(color: String) = println("setting title color $color")

    fun showMessage() = println("showing message")

    fun setMessage(text: String) = println("setting message $text")

    fun setMessageColor(color: String) = println("setting message color $color")

    fun showImage(bitmapBytes: ByteArray) = println("showing image with size ${bitmapBytes.size}")

    fun show() = println("showing dialog $this")
}

class TextView {
   var text: String = ""
   var color: String = "#00000"
}

//Builder:
class DialogBuilder() {
    
    private var titleHolder: TextView? = null
    private var messageHolder: TextView? = null
    private var imageHolder: File? = null
	
	constructor(init: DialogBuilder.() -> Unit) : this() {
        init()
    }

    fun title(init: TextView.() -> Unit) {
        this.titleHolder = TextView().apply { init() }
    }

    fun message(init: TextView.() -> Unit) {
        this.messageHolder = TextView().apply { init() }
    }

    fun image(init: () -> File) {
        this.imageHolder = init()
    }

    fun build(): Dialog {
        val dialog = Dialog()

        titleHolder?.apply {
            dialog.setTitle(text)
            dialog.setTitleColor(color)
            dialog.showTitle()
        }

        messageHolder?.apply {
            dialog.setMessage(text)
            dialog.setMessageColor(color)
            dialog.showMessage()
        }

        imageHolder?.apply {
            dialog.showImage(readBytes())
        }

        return dialog
    }
 
}

//Function that creates dialog builder and builds Dialog

fun dialog(init: DialogBuilder.() -> Unit): Dialog {
    return DialogBuilder(init).build()
}

fun main(args: Array<String>) {

    val dialog: Dialog = dialog {
        title {
            text = "Dialog Title"
        }
		
        message {
            text = "Dialog Message"
            color = "#333333"
        }
		
        image {
            File.createTempFile("image", "jpg")
        }
    }

    dialog.show()
}