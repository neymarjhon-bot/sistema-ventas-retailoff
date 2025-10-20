package com.retailoff;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    
    @Test
    public void testVentaExitosa() {
        VentaService servicio = new VentaService();
        VentaService.ResultadoVenta resultado = servicio.procesarVenta("LAP-001", 1);
        
        assertTrue("La venta debería ser exitosa", resultado.isExito());
        assertTrue("El total debería ser mayor a 0", resultado.getTotal() > 0);
    }
    
    @Test
    public void testVentaSinStock() {
        VentaService servicio = new VentaService();
        VentaService.ResultadoVenta resultado = servicio.procesarVenta("LAP-001", 100);
        
        assertFalse("La venta debería fallar por stock insuficiente", resultado.isExito());
        assertTrue("El mensaje debería indicar stock insuficiente", 
                  resultado.getMensaje().contains("Stock insuficiente"));
    }
    
    @Test
    public void testVentaProductoNoExiste() {
        VentaService servicio = new VentaService();
        VentaService.ResultadoVenta resultado = servicio.procesarVenta("NO-EXISTE", 1);
        
        assertFalse("La venta debería fallar", resultado.isExito());
        assertTrue("El mensaje debería indicar producto no encontrado",
                  resultado.getMensaje().contains("no encontrado"));
    }
    
    @Test
    public void testCalculoDescuento() {
        VentaService servicio = new VentaService();
        
        // Venta con 6 unidades (10% descuento)
        VentaService.ResultadoVenta resultado = servicio.procesarVenta("TEC-003", 6);
        
        assertTrue("Debería aplicar descuento", resultado.getDescuento() > 0);
    }
    
    @Test
    public void testInventarioInicial() {
        Inventario inventario = new Inventario();
        assertFalse("El inventario debería tener productos", inventario.getProductos().isEmpty());
    }
}