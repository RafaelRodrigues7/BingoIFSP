package br.edu.ifsp.scl.rafael.bingoifsp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> numeros = new ArrayList<>();
    TextView nroSorteadoTextView;
    TextView listaSorteados;
    Button sortear;
    Button finalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nroSorteadoTextView = findViewById(R.id.nroSorteado);
        sortear = findViewById(R.id.sortear);
        listaSorteados = findViewById(R.id.listaSorteados);
        finalizar = findViewById(R.id.finalizar);

        if(savedInstanceState != null){
            numeros = savedInstanceState.getIntegerArrayList("PEDRAS_SORTEADAS");
            String numeroAtual = savedInstanceState.getString("NUMERO_ATUAL");

            nroSorteadoTextView.setText(numeroAtual);

            if(numeros.size() > 0){
                int i = 0;
                listaSorteados.setText("");
                Collections.sort(numeros);
                for(i = 0; i < numeros.size(); i++){
                    listaSorteados.append(numeros.get(i) + "   ");
                }
            }

        }else{
            finalizar.setEnabled(false);
        }

        
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Salvar dados de estado dinâmico
        outState.putIntegerArrayList("PEDRAS_SORTEADAS", numeros);
        outState.putString("NUMERO_ATUAL", nroSorteadoTextView.getText().toString());

    }

    public void realizarSorteio(View v){

        if(numeros.size() < 75){

            finalizar.setEnabled(true);

            int repetido = 1;
            int sorteado = 0;
            while(repetido == 1){
                Random gerador = new Random();
                sorteado = gerador.nextInt(75) + 1;

                int i = 0;
                repetido = 0;
                for (i = 0; i < numeros.size(); i++){
                    if(numeros.get(i) == sorteado){
                        repetido = 1;

                    }
                }

            }

            numeros.add(sorteado);

            String letra = "?";
            if(sorteado < 16){
                letra = "B";
            }else{
                if(sorteado < 31){
                    letra = "I";
                }else{
                    if(sorteado < 46){
                        letra = "N";
                    }else{
                        if(sorteado < 61){
                            letra = "G";
                        }else{
                            letra = "O";
                        }
                    }
                }
            }

            nroSorteadoTextView.setText(letra + "-" + sorteado);
        }else{
            nroSorteadoTextView.setText("BINGO");
            sortear.setEnabled(false);

        }


        int i = 0;
        listaSorteados.setText("");
        Collections.sort(numeros);
        for(i = 0; i < numeros.size(); i++){
            listaSorteados.append(numeros.get(i) + "   ");
        }


    }

    public void finalizarSorteio(View v){
        numeros.clear();
        listaSorteados.setText("Nenhum número foi sorteado ainda!");
        nroSorteadoTextView.setText("00");
        sortear.setEnabled(true);
        finalizar.setEnabled(false);

    }

}
