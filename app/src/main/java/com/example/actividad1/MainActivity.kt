package com.example.actividad1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn_Siguiente = findViewById<Button>(R.id.btn_siguiente)
        val ptUsuario = findViewById<EditText>(R.id.pt_Usuario)
        val ptContraseña = findViewById<EditText>(R.id.pt_Contraseña)
        val ptApodo = findViewById<EditText>(R.id.ptApodo)



        btn_Siguiente.setOnClickListener {
            val usuario = ptUsuario.text.toString()
            val contraseña = ptContraseña.text.toString()
            val apodo = ptApodo.text.toString()

            if (usuario == "Cegax") {
                if (contraseña == "123") {
                    val intent = Intent(this, MainActivity2::class.java)
                    intent.putExtra("usuario", usuario)

                    //Guardamos un archivo con ayuda de la clase sharedPreferences, el cual nos va a pedir el nombre
                    val sharedPref = this.getSharedPreferences("MiSharedPreferences", MODE_PRIVATE)
                    //El "MODE_PRIVATE" hace que solo lo puedas ver desde esta aplicación
                    with(sharedPref.edit()) {
                        //Creamos una cadena y le vamos a agregar un valor llamado "apodo" y a este le agregaremos el valor que ingreso el usuario, en este caso es privado
                        putString("apodo", apodo)
                        //Guardamos la configuración que le hicimos al archivo
                        apply()
                    }

                    startActivity(intent)
                } else {
                    //Toast pide 3 parametros, el primero el contexto o el lugar en el que se va a mostrar, después pide el texto a mostrar y por último nos pide la duración del mensaje
                    //Por último para que se muestre se debe mandar a llamar con ".show()"
                    Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                }
            } else {
                //Toast pide 3 parametros, el primero el contexto o el lugar en el que se va a mostrar, después pide el texto a mostrar y por último nos pide la duración del mensaje
                //Por último para que se muestre se debe mandar a llamar con ".show()"
                Toast.makeText(this, "Nombre de usuario no encontrado", Toast.LENGTH_SHORT).show()
            }

        }

        val canciones = findViewById<Button>(R.id.btnCanciones)
        canciones.setOnClickListener {
            val intent = Intent(this, Activiti_Canciones::class.java)
            startActivity(intent)
        }
    }
}