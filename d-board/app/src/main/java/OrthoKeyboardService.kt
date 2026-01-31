package com.example.orthokeyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.KeyEvent
import android.widget.Button

class OrthoKeyboardService : InputMethodService() {

    override fun onCreateInputView(): View {
        val view = layoutInflater.inflate(R.layout.keyboard, null)

        // Row 1
        view.findViewById<Button>(R.id.key_q).setOnClickListener { commitText("q") }
        view.findViewById<Button>(R.id.key_w).setOnClickListener { commitText("w") }
        view.findViewById<Button>(R.id.key_e).setOnClickListener { commitText("e") }
        view.findViewById<Button>(R.id.key_r).setOnClickListener { commitText("r") }
        view.findViewById<Button>(R.id.key_t).setOnClickListener { commitText("t") }
        view.findViewById<Button>(R.id.key_y).setOnClickListener { commitText("y") }
        view.findViewById<Button>(R.id.key_u).setOnClickListener { commitText("u") }
        view.findViewById<Button>(R.id.key_i).setOnClickListener { commitText("i") }
        view.findViewById<Button>(R.id.key_o).setOnClickListener { commitText("o") }
        view.findViewById<Button>(R.id.key_p).setOnClickListener { commitText("p") }

        // Row 2
        view.findViewById<Button>(R.id.key_a).setOnClickListener { commitText("a") }
        view.findViewById<Button>(R.id.key_s).setOnClickListener { commitText("s") }
        view.findViewById<Button>(R.id.key_d).setOnClickListener { commitText("d") }
        view.findViewById<Button>(R.id.key_f).setOnClickListener { commitText("f") }
        view.findViewById<Button>(R.id.key_g).setOnClickListener { commitText("g") }
        view.findViewById<Button>(R.id.key_h).setOnClickListener { commitText("h") }
        view.findViewById<Button>(R.id.key_j).setOnClickListener { commitText("j") }
        view.findViewById<Button>(R.id.key_k).setOnClickListener { commitText("k") }
        view.findViewById<Button>(R.id.key_l).setOnClickListener { commitText("l") }

        // Row 3
        view.findViewById<Button>(R.id.key_z).setOnClickListener { commitText("z") }
        view.findViewById<Button>(R.id.key_x).setOnClickListener { commitText("x") }
        view.findViewById<Button>(R.id.key_c).setOnClickListener { commitText("c") }
        view.findViewById<Button>(R.id.key_v).setOnClickListener { commitText("v") }
        view.findViewById<Button>(R.id.key_enter).setOnClickListener { commitText("\n") }
        view.findViewById<Button>(R.id.key_b).setOnClickListener { commitText("b") }
        view.findViewById<Button>(R.id.key_n).setOnClickListener { commitText("n") }
        view.findViewById<Button>(R.id.key_m).setOnClickListener { commitText("m") }
        view.findViewById<Button>(R.id.key_period).setOnClickListener { commitText(".") }

        // Row 4
        view.findViewById<Button>(R.id.key_delete).setOnClickListener { deleteText() }
        view.findViewById<Button>(R.id.key_space_left).setOnClickListener { commitText(" ") }
        view.findViewById<Button>(R.id.key_up).setOnClickListener { sendKey(KeyEvent.KEYCODE_DPAD_UP) }
        view.findViewById<Button>(R.id.key_down).setOnClickListener { sendKey(KeyEvent.KEYCODE_DPAD_DOWN) }
        view.findViewById<Button>(R.id.key_space_right).setOnClickListener { commitText(" ") }
        view.findViewById<Button>(R.id.key_backspace).setOnClickListener { deleteText() }

        // Row 0 - Arrows
        view.findViewById<Button>(R.id.key_left).setOnClickListener { sendKey(KeyEvent.KEYCODE_DPAD_LEFT) }
        view.findViewById<Button>(R.id.key_right).setOnClickListener { sendKey(KeyEvent.KEYCODE_DPAD_RIGHT) }

        return view
    }

    private fun commitText(text: String) {
        currentInputConnection?.commitText(text, 1)
    }

    private fun deleteText() {
        currentInputConnection?.deleteSurroundingText(1, 0)
    }

    private fun sendKey(keyCode: Int) {
        currentInputConnection?.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, keyCode))
        currentInputConnection?.sendKeyEvent(KeyEvent(KeyEvent.ACTION_UP, keyCode))
    }
}