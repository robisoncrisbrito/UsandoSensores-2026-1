package br.edu.utfpr.usandosensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var tvX: TextView
    private lateinit var tvY: TextView
    private lateinit var tvZ: TextView

    private lateinit var sensorManager: SensorManager
    private lateinit var acelerometro: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvX = findViewById(R.id.tvX)
        tvY = findViewById(R.id.tvY)
        tvZ = findViewById(R.id.tvZ)

        sensorManager = getSystemService( Context.SENSOR_SERVICE ) as SensorManager

        acelerometro = sensorManager.getDefaultSensor( Sensor.TYPE_ACCELEROMETER) as Sensor

        if ( acelerometro != null ) {

            sensorManager.registerListener(
                this,
                acelerometro,
                SensorManager.SENSOR_DELAY_NORMAL
            )

        } else {
            //nao tem o sensor
        }

    }

    override fun onResume() {
        super.onResume()

        if ( acelerometro != null ) {

            sensorManager.registerListener(
                this,
                acelerometro,
                SensorManager.SENSOR_DELAY_NORMAL
            )

        } else {
            //nao tem o sensor
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener( this )
    }

    fun btMeusSensoresOnClick(view: View) {

        val lista = sensorManager.getSensorList(Sensor.TYPE_ALL )

        for ( sensor in lista ) {
            val nome = sensor.name
            Toast.makeText( this, sensor.name, Toast.LENGTH_LONG).show()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(valor: SensorEvent?) {

        val x = valor?.values?.get(0)
        val y = valor?.values?.get(1)
        val z = valor?.values?.get(2)

        tvX.text = x.toString()
        tvY.text = y.toString()
        tvZ.text = z.toString()

    }
}