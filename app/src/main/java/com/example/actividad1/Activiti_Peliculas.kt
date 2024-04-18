package com.example.actividad1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class Activiti_Peliculas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_activiti_peliculas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val pt_titulo = findViewById<EditText>(R.id.pt_tituloPeli)
        val sp_genero = findViewById<Spinner>(R.id.spGenero)
        val btnGuardar = findViewById<Button>(R.id.Continuarpeli)

        btnGuardar.setOnClickListener{
            //primero tomamos los datos que se agregaron como título
            val titulo = pt_titulo.text.toString()

            //Recuperamos datos del spinner
            val genero = sp_genero.selectedItem.toString()

            //Comenzamos con la base de datos de tipo firebase
            //Primero obtenemos la referencia a la base de datos de Firebase(abrimos la base de datos)
            val database = FirebaseDatabase.getInstance()
            //Creamos la tabla en la que se va a guardar el archivo
            val peliculasRef = database.getReference("peliculas")

            //creamos un ID único para la pelicula
            val peliculaId=peliculasRef.push().key

            //creamos el objeto pelicula
            val pelicula = Pelicula(titulo,genero)

            //Guardamos la pelicula en la base de datos usando la referencia hacia Firebase
            peliculaId?.let{
                peliculasRef.child(it).setValue(pelicula)
                    .addOnSuccessListener{
                        Toast.makeText(this, "Se agregó la pelicula", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener{
                        Toast.makeText(this,"Error al guardar la pelicula", Toast.LENGTH_SHORT).show()
                        println("Error al guardar la pelicula en Firebase: ${it.message}")
                    }
            }

            }

        }
}