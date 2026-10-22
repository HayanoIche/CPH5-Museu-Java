package br.com.fiap.museu.repository;

import br.com.fiap.museu.model.Fotografia;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FotografiaRepository
{
    private DataSource dataSource;

    public FotografiaRepository(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public void save(Fotografia fotografia)
    {
        String sql = "INSERT INTO tb_fotos_jv (nome, artista, descricao, numero_de_votos, data_criacao, camera, resolucao_dpi, colorida) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection con = DataSourceUtils.getConnection(dataSource);
        try (PreparedStatement pstmt = con.prepareStatement(sql, new String[]{"id"})) {
            pstmt.setString(1, fotografia.getNome());
            pstmt.setString(2, fotografia.getArtista());
            pstmt.setString(3, fotografia.getDescricao());
            pstmt.setInt(4, fotografia.getNumeroDeVotos());
            pstmt.setDate(5, fotografia.getDataCriacao() != null ? Date.valueOf(fotografia.getDataCriacao()) : null);
            pstmt.setString(6, fotografia.getCamera());
            pstmt.setDouble(7, fotografia.getResolucaoDPI());
            pstmt.setInt(8, fotografia.isColorida() ? 1 : 0);

            int registros = pstmt.executeUpdate();
            if (registros != 1) throw new RuntimeException("Nenhuma fotografia adicionada");

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                fotografia.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro na inserção da fotografia", e);
        } finally {
            DataSourceUtils.releaseConnection(con, dataSource);
        }
    }

}
