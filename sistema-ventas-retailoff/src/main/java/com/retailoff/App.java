package com.retailoff;

/**
 * Clase principal de la aplicación Sistema de Ventas Retail Off
 */
public class App {
    private VentaService ventaService;
    
    public App() {
        this.ventaService = new VentaService();
    }
    
    public void ejecutarDemo() {
        System.out.println("=========================================");
        System.out.println("   SISTEMA DE VENTAS - RETAIL OFF");
        System.out.println("=========================================");
        
        // Mostrar inventario inicial
        ventaService.getInventario().mostrarInventario();
        
        // Simular ventas
        System.out.println("\n=== SIMULACION DE VENTAS ===");
        
        // Venta exitosa
        simularVenta("LAP-001", 2);
        
        // Venta con descuento
        simularVenta("TEC-003", 6);
        
        // Venta sin stock
        simularVenta("MON-002", 20);
        
        // Venta producto no existe
        simularVenta("XYZ-999", 1);
        
        // Mostrar inventario final
        System.out.println("\n=== INVENTARIO FINAL ===");
        ventaService.getInventario().mostrarInventario();
        
        System.out.println("=========================================");
        System.out.println("     DEMO COMPLETADO EXITOSAMENTE");
        System.out.println("=========================================");
    }
    
    private void simularVenta(String codigo, int cantidad) {
        System.out.println("\nProcesando venta: " + codigo + " x " + cantidad);
        VentaService.ResultadoVenta resultado = ventaService.procesarVenta(codigo, cantidad);
        
        if (resultado.isExito()) {
            System.out.println("✅ " + resultado.getMensaje());
            System.out.println("   Total: $" + resultado.getTotal());
            System.out.println("   Descuento: $" + resultado.getDescuento());
        } else {
            System.out.println("❌ " + resultado.getMensaje());
        }
    }
    
    public static void main(String[] args) {
        App sistema = new App();
        sistema.ejecutarDemo();
    }
}