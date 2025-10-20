package com.retailoff;

import java.util.Optional;

/**
 * Servicio para gestionar las ventas
 */
public class VentaService {
    private Inventario inventario;
    
    public VentaService() {
        this.inventario = new Inventario();
    }
    
    public ResultadoVenta procesarVenta(String codigoProducto, int cantidad) {
        if (cantidad <= 0) {
            return new ResultadoVenta(false, "La cantidad debe ser mayor a cero");
        }
        
        if (!inventario.verificarStock(codigoProducto, cantidad)) {
            return new ResultadoVenta(false, "Stock insuficiente para el producto: " + codigoProducto);
        }
        
        Optional<Producto> productoOpt = inventario.buscarProducto(codigoProducto);
        if (productoOpt.isEmpty()) {
            return new ResultadoVenta(false, "Producto no encontrado: " + codigoProducto);
        }
        
        Producto producto = productoOpt.get();
        double subtotal = producto.getPrecio() * cantidad;
        double descuento = calcularDescuento(subtotal, cantidad);
        double total = subtotal - descuento;
        
        // Actualizar stock
        inventario.actualizarStock(codigoProducto, cantidad);
        
        return new ResultadoVenta(true, "Venta exitosa", total, descuento);
    }
    
    private double calcularDescuento(double subtotal, int cantidad) {
        if (cantidad > 10) {
            return subtotal * 0.15; // 15% descuento
        } else if (cantidad > 5) {
            return subtotal * 0.10; // 10% descuento
        } else if (subtotal > 100000) {
            return subtotal * 0.05; // 5% descuento
        }
        return 0;
    }
    
    public Inventario getInventario() {
        return inventario;
    }
    
    // Clase interna para resultado de venta
    public static class ResultadoVenta {
        private boolean exito;
        private String mensaje;
        private double total;
        private double descuento;
        
        public ResultadoVenta(boolean exito, String mensaje) {
            this.exito = exito;
            this.mensaje = mensaje;
        }
        
        public ResultadoVenta(boolean exito, String mensaje, double total, double descuento) {
            this.exito = exito;
            this.mensaje = mensaje;
            this.total = total;
            this.descuento = descuento;
        }
        
        // Getters
        public boolean isExito() { return exito; }
        public String getMensaje() { return mensaje; }
        public double getTotal() { return total; }
        public double getDescuento() { return descuento; }
    }
}