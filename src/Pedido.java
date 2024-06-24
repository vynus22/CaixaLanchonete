import java.util.Date;
import java.util.List;

public class Pedido {
    private String nomeCliente;
    private Date horaPedido;
    private List<Produto> itensPedido;

    public Pedido(String nomeCliente, Date horaPedido, List<Produto> itensPedido) {
        this.nomeCliente = nomeCliente;
        this.horaPedido = horaPedido;
        this.itensPedido = itensPedido;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public Date getHoraPedido() {
        return horaPedido;
    }

    public List<Produto> getItensPedido() {
        return itensPedido;
    }
}