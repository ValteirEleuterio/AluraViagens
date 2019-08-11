package alura.com.aluraviagens.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import alura.com.aluraviagens.R;
import alura.com.aluraviagens.model.Pacote;

import static alura.com.aluraviagens.ui.PacoteActivityConstantes.CHAVE_PACOTE;
import static alura.com.aluraviagens.util.DataUtil.periodoEmTexto;
import static alura.com.aluraviagens.util.DiasUtil.formataEmTexto;
import static alura.com.aluraviagens.util.MoedaUtil.formataParaBrasileiro;
import static alura.com.aluraviagens.util.ResourceUtil.devolveDrawable;

public class ResumoPacoteActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Resumo do pacote";

    TextView data;
    TextView preco;
    TextView dias;
    TextView local;
    ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_pacote);
        setTitle(TITULO_APPBAR);
        carregaPacoteRecebido();
    }

    private void carregaPacoteRecebido() {
        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_PACOTE)) {
            final Pacote pacote = intent.getExtras().getParcelable(CHAVE_PACOTE);
            inicializaCampos(pacote);
            configuraBotao(pacote);
        }
    }

    private void configuraBotao(final Pacote pacote) {
        Button botaoRealizaPagamento = findViewById(R.id.resumo_pacote_botao_realiza_pagamento);
        botaoRealizaPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaiParaPagamento(pacote);
            }
        });
    }

    private void vaiParaPagamento(Pacote pacote) {
        Intent intent = new Intent(ResumoPacoteActivity.this,
                PagamentoActivity.class);
        intent.putExtra(CHAVE_PACOTE, pacote);
        startActivity(intent);
    }

    private void inicializaCampos(Pacote pacote) {
        bind();
        mostraImagem(pacote);
        mostraLocal(pacote);
        mostraDias(pacote);
        mostraPreco(pacote);
        mostraData(pacote);
    }

    private void bind() {
        data = findViewById(R.id.resumo_pacote_data);
        preco = findViewById(R.id.resumo_pacote_preco);
        dias = findViewById(R.id.resumo_pacote_dias);
        local = findViewById(R.id.resumo_pacote_local);
        imagem = findViewById(R.id.resumo_pacote_imagem);
    }

    private void mostraData(Pacote pacote) {
        String dataFormatadaDaViagem = periodoEmTexto(pacote.getDias());
        data.setText(dataFormatadaDaViagem);
    }

    private void mostraPreco(Pacote pacote) {
        String moedaBrasileira = formataParaBrasileiro(pacote.getPreco());
        preco.setText(moedaBrasileira);
    }

    private void mostraDias(Pacote pacote) {
        String diasEmTexto = formataEmTexto(pacote.getDias());
        dias.setText(diasEmTexto);
    }

    private void mostraLocal(Pacote pacote) {
        local.setText(pacote.getLocal());
    }

    private void mostraImagem(Pacote pacote) {
        Drawable drawableDevolvido = devolveDrawable(this, pacote.getImagem());
        imagem.setImageDrawable(drawableDevolvido);
    }
}
