package tests;


import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;

import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ArchiveTests {

    /*
    – Запаковать в zip архив несколько разных файлов - pdf, xlsx, csv
    – Положить его в ресурсы
    – Реализовать чтение и проверку содержимого каждого файла из архива
    2.
    – Реализовать разбор json файла библиотекой Jackson https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core/2.13.1
    – Придумать реальный объект и описать его в виде json
    – В идеале json должен содержать массив
    */

    private ClassLoader cl = ArchiveTests.class.getClassLoader();


    @DisplayName("Проверка содержимого архивированных файлов (отключен, плохая практика)")
    @Disabled
    @Test
    void checkFilesIntoZipTest() throws Exception {

        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("dkp.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf"))
                {
                    PDF pdf = new PDF(zis);
                    Assertions.assertEquals(
                            "Тестовый PDF \r\n"
                            ,pdf.text);
                }

                if (entry.getName().endsWith(".xlsx"))
                {
                    XLS xls = new XLS(zis);
                    String res1 = String.valueOf(xls.excel.getSheetAt(0).getRow(4).getCell(3).getNumericCellValue());
                    Assertions.assertEquals("1.0",res1);
                }

                if (entry.getName().endsWith(".csv"))
                {
                    CSVReader csv = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = csv.readAll();
                    Assertions.assertEquals(6, data.size());
                    Assertions.assertArrayEquals(
                            new String[] {"key","stack_size","image_index","sprite_index","type","category","nameen","nameru"},
                            data.get(0)
                            );
                }
            }
        }
    }

    @DisplayName("Проверка содержимого PDF файла внутри архива")
    @Test
    void checkPDFIntoZipTest() throws Exception {

        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("dkp.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertEquals(
                            "Тестовый PDF \r\n"
                            , pdf.text);
                }
            }
        }
    }

    @DisplayName("Проверка содержимого XLSX файла внутри архива")
    @Test
    void checkXlsxIntoZipTest() throws Exception {

        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("dkp.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xlsx"))
                {
                    XLS xls = new XLS(zis);
                    String res1 = String.valueOf(xls.excel.getSheetAt(0).getRow(4).getCell(3).getNumericCellValue());
                    Assertions.assertEquals("1.0",res1);
                }
            }
        }
    }

    @DisplayName("Проверка содержимого Csv файла внутри архива")
    @Test
    void checkCsvIntoZipTest() throws Exception {

        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("dkp.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv"))
                {
                    CSVReader csv = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = csv.readAll();
                    Assertions.assertEquals(6, data.size());
                    Assertions.assertArrayEquals(
                            new String[] {"key","stack_size","image_index","sprite_index","type","category","nameen","nameru"},
                            data.get(0)
                    );
                }
            }
        }
    }

    @DisplayName("Проверка содержимого json файла внутри архива")
    @Test
    void checkJSONIntoZipTest() throws Exception {
        boolean foundJson = false;
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("dkp.zip"))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".json")) {
                    foundJson = true;

                    // Читаем entry полностью в память
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int len;
                    while ((len = zis.read(buffer)) != -1) {
                        baos.write(buffer, 0, len);
                    }
                    byte[] jsonBytes = baos.toByteArray();

                    ObjectMapper objectMapper = new ObjectMapper();

                    List<WeaponParts> parts = objectMapper.readValue(jsonBytes, new TypeReference<List<WeaponParts>>() {});

                    assertThat(parts).hasSize(2);
                    WeaponParts part1 = parts.get(0);
                    assertThat(part1.key).isEqualTo("regular_blade_bad");
                    assertThat(part1.stack_size).isEqualTo(1);
                    assertThat(part1.sprite_index).isEqualTo("spr_ex_item_stuff_blade");
                    assertThat(part1.type).containsExactly("sword", "regular", "bad");
                    assertThat(part1.local.name_en).isEqualTo("Sword blade bad");
                    assertThat(part1.local.name_ru).isEqualTo("Лезвие меча плохое");
                    assertThat(part1.weight).isEqualTo(1.5);

                    WeaponParts part2 = parts.get(1);
                    assertThat(part2.key).isEqualTo("regular_blade");
                    assertThat(part2.type).containsExactly("sword", "regular");
                    assertThat(part2.local.name_ru).isEqualTo("Лезвие меча");
                }
            }
        }
        assertThat(foundJson).as("JSON file must be found in zip").isTrue();
    }





}
