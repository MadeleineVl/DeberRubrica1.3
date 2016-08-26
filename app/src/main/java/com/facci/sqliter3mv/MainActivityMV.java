package com.facci.sqliter3mv;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityMV extends AppCompatActivity {

     DBHelper dbSQLITE;
        EditText txtNombre_MMVL,txtApellido_MMVL,txtRecintoElectoral_MMVL,txtID_MMVL,txtAnoNacimiento_MMVL;

    Button btnIngresar,btnModificar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_mv);
        dbSQLITE=new DBHelper(this);
    }

    public void InsertarClick (View v){
        txtNombre_MMVL = (EditText) findViewById(R.id.txtNombre_MMVL);
        txtApellido_MMVL = (EditText) findViewById(R.id.txtApellido_MMVL);
        txtRecintoElectoral_MMVL = (EditText) findViewById(R.id.txtRecintoElectoral_MMVL);
        txtAnoNacimiento_MMVL = (EditText) findViewById(R.id.txtAnoNacimiento_MMVL);
        boolean estaInsertado = dbSQLITE.Insertar(txtNombre_MMVL.getText().toString(),txtApellido_MMVL.getText().toString(),txtRecintoElectoral_MMVL.getText().toString(),Integer.parseInt(txtAnoNacimiento_MMVL.getText().toString()));

        if(estaInsertado)
            Toast.makeText(MainActivityMV.this, "Datos correctamente Ingresados", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivityMV.this,"Ocurrido un error",Toast.LENGTH_SHORT).show();
    }
    public void Mensaje(String titulo, String Mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(Mensaje);
        builder.show();

    }
    public void modificarClick(View v){
        txtNombre_MMVL = (EditText) findViewById(R.id.txtNombre_MMVL);
        txtApellido_MMVL = (EditText) findViewById(R.id.txtApellido_MMVL);
        txtRecintoElectoral_MMVL = (EditText) findViewById(R.id.txtRecintoElectoral_MMVL);
        txtAnoNacimiento_MMVL = (EditText) findViewById(R.id.txtAnoNacimiento_MMVL);
        txtID_MMVL = (EditText) findViewById(R.id.txtID_MMVL);

        boolean estaAcutalizado = dbSQLITE.Modificar(txtID_MMVL.getText().toString(), txtNombre_MMVL.getText().toString(), txtApellido_MMVL.getText().toString(), txtRecintoElectoral_MMVL.getText().toString(), Integer.parseInt(txtAnoNacimiento_MMVL.getText().toString()));
        if (estaAcutalizado == true){
            Toast.makeText(MainActivityMV.this,"Registro Actualizado",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityMV.this,"ERROR: Registro NO Actualizado",Toast.LENGTH_SHORT).show();
        }
    }
    public void verTodosClick(View v){

        Cursor res = dbSQLITE.VerTodos();
        if(res.getCount() == 0){
            Mensaje("Error", "No hay registros");
            return;
        }
        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext()){
            buffer.append("Id : "+res.getString(0)+"\n");
            buffer.append("Nombre : "+res.getString(1)+"\n");
            buffer.append("Apellido : "+res.getString(2)+"\n");
            buffer.append("Recinto Electoral : "+res.getString(3)+"\n");
            buffer.append("Año  de Nacimiento : "+res.getInt(4)+"\n\n");
        }

       Mensaje("Registros",buffer.toString());
    }
    public void EliminarClick(View v){

        txtID_MMVL = (EditText) findViewById(R.id.txtID_MMVL);
        Integer registrosEliminados = dbSQLITE.Eliminar(txtID_MMVL.getText().toString());

        if(registrosEliminados > 0 ){
            Toast.makeText(MainActivityMV.this,"Registro(s) Eliminado(s)",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityMV.this,"ERROR: Registro no eliminado",Toast.LENGTH_SHORT).show();
        }

    }
}
