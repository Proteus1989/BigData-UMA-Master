package org.uma.mbd.mdLibreriaV1.libreria;

import java.util.Arrays;

public class Libreria {
    private Libro[] libros;
    private int numLibros;
    private static final int TAM_DEFECTO = 16;

    public Libreria()
    {
        this(TAM_DEFECTO);
    }

    public Libreria(int max)
    {
        libros = new Libro[max];
        numLibros = 0;
    }

    private void aseguraQueCabe()
    {
        if(numLibros == libros.length)
            libros = Arrays.copyOf(libros, numLibros*2);
    }

    public void addLibro(String autor, String titulo, double precioBase)
    {
        int i = posicionLibro(autor, titulo);
        Libro libro = new Libro(autor, titulo, precioBase);
        if (i<0)
            addLibro(libro);
        else
            libros[i] = libro;
    }

    private void addLibro(Libro libro)
    {
        aseguraQueCabe();
        libros[numLibros] = libro;
        numLibros++;
    }

    private int posicionLibro(String autor, String titulo)
    {
        int pos = 0;
        while (pos < numLibros && !(autor.equalsIgnoreCase(libros[pos].getAutor()) &&
                titulo.equalsIgnoreCase(libros[pos].getTitulo())))
        {
            pos++;
        }

        return pos == numLibros ? -1 : pos;
    }

    public void remLibro(String autor, String titulo)
    {
        int posicion = posicionLibro(autor, titulo);

        if(posicion >= 0)
        {
            for(int i = posicion + 1; i<numLibros; i++)
                libros[i - 1] = libros[i];

            numLibros--;
        }
    }

    public double getPrecioBase(String autor, String titulo)
    {
        int pos = posicionLibro(autor,titulo);
        return (pos >= 0) ? libros[pos].getPrecioBase() : 0;
    }

    public double getPrecioFinal(String autor, String titulo)
    {
        int pos = posicionLibro(autor,titulo);
        return (pos >= 0) ? libros[pos].getPrecioFinal() : 0;
    }

    @Override
    public String toString() {
        String salida = "[";
        for(int i=0; i<numLibros; i++)
        {
            salida += libros[i];
            if(i < numLibros -1)
            {
                salida += ", ";
            }
        }

        return salida + "]";
    }
}