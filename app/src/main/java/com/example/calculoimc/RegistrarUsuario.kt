package com.example.calculoimc

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculoimc.databinding.ActivityRegistrarUsuarioBinding

class RegistrarUsuario : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrarUsuarioBinding
    var imagen =" "

    val guardarUsuario = GuardarUsuario(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistrarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonRegister.setOnClickListener {
            guardarUsuario()
        }
        binding.buttonseleccionarimagen.setOnClickListener {
            seleccionarImagen()
        }

    }

    private fun seleccionarImagen() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        Register.launch(intent)

    }

    val Register = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            val uri = it.data?.data
            imagen = uri.toString()
            binding.imageView.setImageURI(uri)
        }
    }

    private fun guardarUsuario() {
        val nombre = binding.editTextNombre.text.toString()
        val apellido = binding.apellido.text.toString()
        val peso = binding.peso.text.toString().toDouble()
        val estatura = binding.estatura.text.toString().toDouble()

        val data = Data(nombre, apellido, peso, estatura,imagen)

        guardarUsuario.guardarUsuario(data)
        val intent = Intent(this, IMC::class.java)
        intent.putExtra("nombre", data)
        startActivity(intent)
        Toast.makeText(this, "usuario registrado", Toast.LENGTH_SHORT).show()

    }

}