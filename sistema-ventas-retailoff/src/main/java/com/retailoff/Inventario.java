package com.retailoff;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase para gestionar el inventario de productos
 */
public class Inventario {
    private List<Producto> productos;
    
    public Inventario() {
        this.productos = new ArrayList<>();
        // Productos iniciales para pruebas
        inicializarProductos();
    }
    
    private void inicializarProductos() {
        agregarProducto(new Producto("LAP-001", "Laptop Dell Inspiron", 450000, 10));
        agregarProducto(new Producto("MON-002", "Monitor 24\" Samsung", 150000, 15));
        agregarProducto(new Producto("TEC-003", "Teclado Mecánico", 75000, 20));
        agregarProducto(new Producto("MOU-004", "Mouse Inalámbrico", 25000, 30));
    }
    
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
    
    public Optional<Producto> buscarProducto(String codigo) {
        return productos.stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findFirst();
    }
    
    public boolean verificarStock(String codigo, int cantidad) {
        Optional<Producto> producto = buscarProducto(codigo);
        return producto.isPresent() && producto.get().getStock() >= cantidad;
    }
    
    public boolean actualizarStock(String codigo, int cantidadVendida) {
        Optional<Producto> producto = buscarProducto(codigo);
        if (producto.isPresent() && producto.get().getStock() >= cantidadVendida) {
            Producto p = producto.get();
            p.setStock(p.getStock() - cantidadVendida);
            return true;
        }
        return false;
    }
    
    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }
    
    public void mostrarInventario() {
        System.out.println("=== INVENTARIO RETAIL OFF ===");
        for (Producto p : productos) {
            System.out.println(p);
        }
        System.out.println("==============================");
    }
}