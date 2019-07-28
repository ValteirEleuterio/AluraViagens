package alura.com.aluraviagens.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;

import alura.com.aluraviagens.R;
import alura.com.aluraviagens.model.Pacote;

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
        bind();

        setTitle(TITULO_APPBAR);

        Pacote pacoteSaoPaulo = new Pacote("São Paulo",
                "sao_paulo_sp", 2, new BigDecimal("243.99"));

        mostraImagem(pacoteSaoPaulo);
        mostraLocal(pacoteSaoPaulo);
        mostraDias(pacoteSaoPaulo);
        mostraPreco(pacoteSaoPaulo);
        mostraData(pacoteSaoPaulo);
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
