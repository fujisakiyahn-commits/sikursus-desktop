
package report;

import config.Database;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ReportService {

    public static void tampilkanLaporanPeserta() {

        String reportPath = "/report/laporan_peserta.jasper";

        try (Connection conn = Database.getConnection();
             InputStream reportStream =
                     ReportService.class.getResourceAsStream(reportPath)) {

            if (conn == null) {
                JOptionPane.showMessageDialog(
                    null,
                    "Koneksi database gagal!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (reportStream == null) {
                JOptionPane.showMessageDialog(
                    null,
                    "File laporan tidak ditemukan: " + reportPath,
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            HashMap<String, Object> parameters = new HashMap<>();

            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                        reportStream,
                        parameters,
                        conn
                    );

            // Periksa apakah laporan memiliki halaman
            if (jasperPrint.getPages().isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,
                    "Laporan berhasil diproses, tetapi tidak memiliki halaman."
                    + "\nPeriksa query SQL dan data peserta di database.",
                    "Laporan Kosong",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            JasperViewer viewer =
                    new JasperViewer(jasperPrint, false);

            viewer.setVisible(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                null,
                "Gagal menampilkan laporan: " + ex.getMessage(),
                "Error Cetak",
                JOptionPane.ERROR_MESSAGE
            );

            ex.printStackTrace();
        }
    }
}
