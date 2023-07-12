/**
 * Performs handwritten text detection on a local image file.
 *
 * @param filePath The path to the local file to detect handwritten text on.
 * @param out A {@link PrintStream} to write the results to.
 * @throws Exception on errors while closing the client.
 * @throws IOException on Input/Output errors.
 */

package kr.com.smhrd;

import java.io.IOException;
import java.io.PrintStream;

public static void detectHandwrittenOcr(String filePath, PrintStream out) throws Exception {
  List<AnnotateImageRequest> requests = new ArrayList<>();

  ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

  Image img = Image.newBuilder().setContent(imgBytes).build();
  Feature feat = Feature.newBuilder().setType(Type.DOCUMENT_TEXT_DETECTION).build();
  // Set the Language Hint codes for handwritten OCR
  ImageContext imageContext =
      ImageContext.newBuilder().addLanguageHints("en-t-i0-handwrit").build();

  AnnotateImageRequest request =
      AnnotateImageRequest.newBuilder()
          .addFeatures(feat)
          .setImage(img)
          .setImageContext(imageContext)
          .build();
  requests.add(request);

  try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
    BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
    List<AnnotateImageResponse> responses = response.getResponsesList();
    client.close();

    for (AnnotateImageResponse res : responses) {
      if (res.hasError()) {
        out.printf("Error: %s\n", res.getError().getMessage());
        return;
      }

      // For full list of available annotations, see http://g.co/cloud/vision/docs
      TextAnnotation annotation = res.getFullTextAnnotation();
      for (Page page : annotation.getPagesList()) {
        String pageText = "";
        for (Block block : page.getBlocksList()) {
          String blockText = "";
          for (Paragraph para : block.getParagraphsList()) {
            String paraText = "";
            for (Word word : para.getWordsList()) {
              String wordText = "";
              for (Symbol symbol : word.getSymbolsList()) {
                wordText = wordText + symbol.getText();
                out.format(
                    "Symbol text: %s (confidence: %f)\n",
                    symbol.getText(), symbol.getConfidence());
              }
              out.format("Word text: %s (confidence: %f)\n\n", wordText, word.getConfidence());
              paraText = String.format("%s %s", paraText, wordText);
            }
            // Output Example using Paragraph:
            out.println("\nParagraph: \n" + paraText);
            out.format("Paragraph Confidence: %f\n", para.getConfidence());
            blockText = blockText + paraText;
          }
          pageText = pageText + blockText;
        }
      }
      out.println("\nComplete annotation:");
      out.println(annotation.getText());
    }
  }
}