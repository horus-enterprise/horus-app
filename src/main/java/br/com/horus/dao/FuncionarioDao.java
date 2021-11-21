package br.com.horus.dao;

import br.com.horus.model.Funcionario;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import static javax.swing.JOptionPane.showMessageDialog;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class FuncionarioDao extends Dao {

    JdbcTemplate con;

    public FuncionarioDao() {
        this.con = new JdbcTemplate(getDataSource());
    }

    public Funcionario listar(String email, String senha) {
        String sql = "";
        sql = "SELECT * FROM Funcionario WHERE email = ? AND senha = ?";
        List<Funcionario> funcionario = con.query(sql,
                new BeanPropertyRowMapper(Funcionario.class), email, senha);

        if (funcionario.isEmpty()) {
            return null;
        }
        return funcionario.get(0);
    }

    public void redefinirSenha(String email, String senha, String novaSenha) {
        Funcionario funcionario = listar(email, senha);
            if (funcionario != null) {
                con.update("UPDATE Funcionario SET senha = ? WHERE idFuncionario = ?", novaSenha, funcionario.getIdFuncionario());
                showMessageDialog(null, "Senha atualizada com sucesso!");
            } else {
                showMessageDialog(null, "Os dados informados estão incorreto!\n"
                        + "Verifique e tente novamente ou contate seu adiministrador.");
            }
    }
}
