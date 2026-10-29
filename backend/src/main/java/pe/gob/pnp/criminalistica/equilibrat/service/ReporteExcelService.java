package pe.gob.pnp.criminalistica.equilibrat.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pe.gob.pnp.criminalistica.equilibrat.dao.PeritoDAO;
import pe.gob.pnp.criminalistica.equilibrat.model.Perito;

/**
 * Exportación Excel de la tabla de fatiga con Apache POI.
 */
public class ReporteExcelService {

    private final PeritoDAO peritoDAO;
    private final CalculadoraSaludService calculadoraSaludService;

    public ReporteExcelService(PeritoDAO peritoDAO, CalculadoraSaludService calculadoraSaludService) {
        this.peritoDAO = peritoDAO;
        this.calculadoraSaludService = calculadoraSaludService;
    }

    public byte[] exportarTablaFatiga() {
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream salida = new ByteArrayOutputStream()) {
            Sheet hoja = workbook.createSheet("Fatiga ocupacional");
            CellStyle estiloCabecera = crearEstiloCabecera(workbook);

            String[] cabeceras = {"CIP", "Nombre", "Departamento", "Rol", "Fatiga (%)", "Nivel de riesgo"};
            Row filaCabecera = hoja.createRow(0);
            for (int i = 0; i < cabeceras.length; i++) {
                Cell celda = filaCabecera.createCell(i);
                celda.setCellValue(cabeceras[i]);
                celda.setCellStyle(estiloCabecera);
            }

            int filaIdx = 1;
            for (Perito perito : peritoDAO.listarTodos()) {
                Row fila = hoja.createRow(filaIdx++);
                fila.createCell(0).setCellValue(perito.getCip());
                fila.createCell(1).setCellValue(perito.getNombre());
                fila.createCell(2).setCellValue(perito.getDepartamento());
                fila.createCell(3).setCellValue(perito.getRol().name());
                fila.createCell(4).setCellValue(perito.getNivelFatiga());
                fila.createCell(5).setCellValue(calculadoraSaludService.clasificarFatiga(perito.getNivelFatiga()).name());
            }

            for (int i = 0; i < cabeceras.length; i++) {
                hoja.autoSizeColumn(i);
            }
            workbook.write(salida);
            return salida.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo generar el reporte Excel", e);
        }
    }

    private CellStyle crearEstiloCabecera(XSSFWorkbook workbook) {
        CellStyle estilo = workbook.createCellStyle();
        Font fuente = workbook.createFont();
        fuente.setBold(true);
        fuente.setColor(IndexedColors.WHITE.getIndex());
        estilo.setFont(fuente);
        estilo.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return estilo;
    }
}
