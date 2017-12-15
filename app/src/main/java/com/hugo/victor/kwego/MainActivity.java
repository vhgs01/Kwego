package com.hugo.victor.kwego;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hugo.victor.kwego.utils.conversions.convertRomanToDecimal;
import static com.hugo.victor.kwego.utils.conversions.convertToRoman;

public class MainActivity extends AppCompatActivity {

    //    FAZ O BIND DOS ELEMENTOS USANDO A BUTTER KNIFE
    @BindView(R.id.kwegonianNumbers)
    TextInputLayout kwegonianNumbers;

    @BindView(R.id.tvRomano)
    TextView tvRomano;

    @BindView(R.id.tvDecimal)
    TextView tvDecimal;

    //    CRIAÇÃO DE VARIÁVEIS
    String[] numeroKwegonianNumbers;
    String numeroRomano, aux;
    Integer numeroDecimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        INICIALIZA A BIBLIOTECA BUTTER KNIFE PARA ESSE CONTEXTO
        ButterKnife.bind(this);

    }

    //    EVENTO DE CLICK DO BOTÃO TRADUZIR
    @OnClick(R.id.btnTraduzir)
    public void convertToDecimal() {
//        RESETA AS VARIÁVEIS
        aux = null;
        numeroRomano = "";
        numeroDecimal = null;

//        CASO O CAMPO DO NÚMERO KWEGONIAN ESTEJA VAZIO EXIBE UM ERRO PARA O USUÁRIO
        if (isEmpty(kwegonianNumbers)) {
            kwegonianNumbers.setError(getString(R.string.txtErrorInvalidKwegonian));
        } else {
//            LIMPA O ERRO
            kwegonianNumbers.setError(null);
//            ATRIBUI O QUE FOI DIGITADO A UMA VARIÁVEL SEPARANDO POR ESPAÇO
            numeroKwegonianNumbers = kwegonianNumbers.getEditText().getText().toString().split("\\s+");
//            PARA CADA STRING (NÚMERO KWEGONIAN) DIGITADO EU PEGO SEU REFERENTE ROMANO
            for (int i = 0; i < numeroKwegonianNumbers.length; i++) {
//                ATRIBUI A UMA VARIÁVEL AUXILIAR O VALOR ROMANO
                aux = convertToRoman(numeroKwegonianNumbers[i]);

//                CASO EXISTA O REFERENTE ROMANO ADICIONA-O NUMA VARIÁVEL
                if (aux != null) {
                    numeroRomano += aux;
                } else {
//                CASO NÃO TENHA SIDO ENCONTRADO O REFERENTE ROMANO EXIBE UM ERRO AO USUÁRIO
                    kwegonianNumbers.setError(getString(R.string.txtErrorInvalidKwegonian));
                    break;
                }
            }

//            FUNÇÃO QUE CONVERTE PARA DECIMAL A PARTIR DE UMA STRING
            numeroDecimal = convertRomanToDecimal(numeroRomano);
//            ATRIBUI NO TEXT VIEW O NÚMERO EM ROMANO
            tvRomano.setText(numeroRomano);
//            ATRIBUI NO TEXT VIEW O NÚMERO EM DECIMAL
            tvDecimal.setText(Integer.toString(numeroDecimal));
        }
    }

    //    EVENTO DE CLICK NO BOTÃO RESETAR
    @OnClick(R.id.btnResetar)
    public void resetar() {
//        LIMPA OS CAMPOS
        kwegonianNumbers.setError(null);
        kwegonianNumbers.getEditText().setText(null);
        tvRomano.setText("");
        tvDecimal.setText("");
    }

    //    FUNÇÃO PARA VERIFICAR SE UM CAMPO ESTÁ VAZIO
    private boolean isEmpty(final TextInputLayout campo) {
        return campo.getEditText().getText().toString().isEmpty();
    }
}
