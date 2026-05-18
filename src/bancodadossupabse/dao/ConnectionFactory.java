package bancodadossupabse.dao;

import java.sql.Connection;
import bancodadossupabse.ConexaoSupaBase;

public class ConnectionFactory {

    public static Connection getConnection() throws Exception {
        return ConexaoSupaBase.obterConexao();
    }
}
