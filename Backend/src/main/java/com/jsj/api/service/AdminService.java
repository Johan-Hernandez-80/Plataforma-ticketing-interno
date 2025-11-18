/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jsj.api.entity.dao.TicketDAO;
import com.jsj.api.entity.dao.UsuarioDAO;
import java.io.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan José Molano Franco
 */
@Service
public class AdminService {

    private final TicketDAO ticketDao;
    private final UsuarioDAO usuarioDao;

    public AdminService(TicketDAO ticketDao, UsuarioDAO usuarioDao) {
        this.ticketDao = ticketDao;
        this.usuarioDao = usuarioDao;
    }

    public long countTicketsAbiertos() {
        return ticketDao.countTicketsAbiertos();
    }

    public long countTicketsCerrados() {
        return ticketDao.countTicketsCerrados();
    }

    public long countEmpleadosActivos() {
        return usuarioDao.countEmpleadosActivos();
    }

    public long countAgentesActivos() {
        return usuarioDao.countAgentesActivos();
    }

    public byte[] generateSystemReportPdf() throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();

        document.add(new Paragraph("Informe del Sistema"));
        document.add(new Paragraph("Tickets abiertos: " + countTicketsAbiertos()));
        document.add(new Paragraph("Tickets cerrados: " + countTicketsCerrados()));
        document.add(new Paragraph("Empleados activos: " + countEmpleadosActivos()));
        document.add(new Paragraph("Agentes activos: " + countAgentesActivos()));

        document.close();
        return baos.toByteArray();
    }
}
