package alura.com.aluraviagens.ui.activity;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;

import alura.com.aluraviagens.R;
import alura.com.aluraviagens.model.Pacote;
import alura.com.aluraviagens.util.DataUtil;
import alura.com.aluraviagens.util.MoedaUtil;
import alura.com.aluraviagens.util.ResourceUtil;

public class ResumoCompraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_compra);

        Pacote pacoteSaoPaulo = new Pacote("São Paulo",
                "sao_paulo_sp", 2, new BigDecimal("243.99"));


        ImageView imagem = findViewById(R.id.resumo_compra_imagem_pacote);
        Drawable drawableDevolvido = ResourceUtil
                .devolveDrawable(this, pacoteSaoPaulo.getImagem());
        imagem.setImageDrawable(drawableDevolvido);

        TextView local = findViewById(R.id.resumo_compra_local_pacote);
        local.setText(pacoteSaoPaulo.getLocal());

        TextView data = findViewById(R.id.resumo_compra_data_viagem);
        String periodoEmTexto = DataUtil
                .periodoEmTexto(pacoteSaoPaulo.getDias());
        data.setText(periodoEmTexto);

        TextView preco = findViewById(R.id.resumo_compra_preco_pacote);
        String moedaBrasileira = MoedaUtil
                .formataParaBrasileiro(pacoteSaoPaulo.getPreco());
        preco.setText(moedaBrasileira);
    }
}
