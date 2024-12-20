package com.example.calculoimc

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculoimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonRegister.setOnClickListener {
            val intent = Intent(this, RegistrarUsuario::class.java)
            startActivity(intent)
        }
        binding.buttonLogin.setOnClickListener {
            iniciarsesions()
            val intent = Intent(this, IMC::class.java)
            startActivity(intent)
        }


    }

    fun iniciarsesions() {
        val nombre = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()
        val guardarUsuario = GuardarUsuario(this)
        val data = guardarUsuario.obtenerUsuario(nombre)
        if (data != null) {
            if (data.nombre == nombre && data.apellido == password) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, IMC::class.java)
                intent.putExtra("nombre", data)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Inicio de sesión fallido", Toast.LENGTH_SHORT).show()
            }

        }


    }


}