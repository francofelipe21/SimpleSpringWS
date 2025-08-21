package com.sonda.prueba_tecnica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class PruebaTecnicaApplication {
	public static Map<Integer, Short> BinaryOperations = new HashMap<>();
	private static void initializeDictionary() {
		try (InputStream inputStream = new FileInputStream("OperacionesBinarias.bin");
			 DataInputStream dataInput = new DataInputStream(inputStream)) {
			byte[] tuple_bin = new byte[9];
			while (true) {
				try {
					dataInput.readFully(tuple_bin);
					byte[] record_bin = Arrays.copyOfRange(tuple_bin, 0, 8);
					byte[] operation_bin = Arrays.copyOfRange(tuple_bin, 7, 9);
					int record_int = ByteBuffer.wrap(record_bin).order(java.nio.ByteOrder.LITTLE_ENDIAN).getInt();
					short operation_int = ByteBuffer.wrap(operation_bin).order(java.nio.ByteOrder.LITTLE_ENDIAN).getShort();
					BinaryOperations.put(record_int, operation_int);
				} catch (EOFException e) {
					break;
				}
			}
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public static void main(String[] args) throws FileNotFoundException {
		initializeDictionary();
        SpringApplication.run(PruebaTecnicaApplication.class, args);
		System.out.println("Server working");
	}



}
