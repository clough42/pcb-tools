package com.clough42.bom.panelizer;

import com.clough42.bom.panelizer.panelizer.Panelizer;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;

public class BomPanelizer {

  private static Panelizer panelizer = new Panelizer();

  public static void main(String[] args) {
    Options options = new Options()
      .addRequiredOption("b", "bomFile", true, "Bill of materials file (CSV)")
      .addRequiredOption("c", "centroidFile", true, "Pick-and-place (centroid) file (CSV)")
      .addRequiredOption("p", "panelFile", true, "Panel definition file (CSV)")
      .addRequiredOption("o", "outputDir", true, "Output directory");

    CommandLineParser parser = new DefaultParser();
    try {
      CommandLine commandLine = parser.parse(options, args);

      File bomFile = new File(commandLine.getOptionValue("b"));
      File centroidFile = new File(commandLine.getOptionValue("c"));
      File panelFile = new File(commandLine.getOptionValue("p"));
      File outputDir = new File(commandLine.getOptionValue("o"));

      if (!bomFile.canRead()) {
        throw new IllegalArgumentException("Cannot read file: " + bomFile);
      }
      if (!centroidFile.canRead()) {
        throw new IllegalArgumentException("Cannot read file: " + centroidFile);
      }
      if (!panelFile.canRead()) {
        throw new IllegalArgumentException("Cannot read file: " + panelFile);
      }

      if (!outputDir.exists()) {
        outputDir.mkdir();
      }
      if (!outputDir.isDirectory()) {
        throw new IllegalArgumentException("Output directory does not exist and cannot be created: " + outputDir);
      }
      if (!outputDir.canWrite()) {
        throw new IllegalArgumentException("Output directory is not writable: " + outputDir);
      }

      File outputBomFile = new File(outputDir, bomFile.getName());
      if (outputBomFile.exists() && !outputBomFile.canWrite()) {
        throw new IllegalArgumentException("Cannot write output bom file: " + outputBomFile);
      }

      File outputCentroidFile = new File(outputDir, centroidFile.getName());
      if (outputCentroidFile.exists() && !outputCentroidFile.canWrite()) {
        throw new IllegalArgumentException("Cannot write output centroid file: " + outputCentroidFile);
      }

      System.out.println("BOM PANELIZER");
      System.out.println("----------------------------------------------------------------------");
      System.out.println("             Input BOM: " + bomFile.getAbsolutePath());
      System.out.println("  Input Pick-and-place: " + centroidFile.getAbsolutePath());
      System.out.println("     Panel description: " + panelFile.getAbsolutePath());
      System.out.println("            Output BOM: " + outputBomFile.getAbsolutePath());
      System.out.println(" Output Pick-and-place: " + outputCentroidFile.getAbsolutePath());
      System.out.println("----------------------------------------------------------------------");
      
      panelizer.panelize(bomFile, centroidFile, panelFile, outputBomFile, outputCentroidFile);
    } catch (ParseException e) {
      System.out.println(e.getMessage());
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("bom-panelizer", options);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void setPanelizerForTesting(Panelizer panelizer) {
    BomPanelizer.panelizer = panelizer;
  }

}
