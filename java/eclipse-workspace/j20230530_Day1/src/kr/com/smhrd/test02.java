package kr.com.smhrd;

import com.google.cloud.vision.v1p3beta1.AnnotateImageRequest;
import com.google.cloud.vision.v1p3beta1.AnnotateImageResponse;
import com.google.cloud.vision.v1p3beta1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1p3beta1.Block;
import com.google.cloud.vision.v1p3beta1.Feature;
import com.google.cloud.vision.v1p3beta1.Feature.Type;
import com.google.cloud.vision.v1p3beta1.Image;
import com.google.cloud.vision.v1p3beta1.ImageAnnotatorClient;
import com.google.cloud.vision.v1p3beta1.ImageContext;
import com.google.cloud.vision.v1p3beta1.ImageSource;
import com.google.cloud.vision.v1p3beta1.LocalizedObjectAnnotation;
import com.google.cloud.vision.v1p3beta1.Page;
import com.google.cloud.vision.v1p3beta1.Paragraph;
import com.google.cloud.vision.v1p3beta1.Symbol;
import com.google.cloud.vision.v1p3beta1.TextAnnotation;
import com.google.cloud.vision.v1p3beta1.Word;
import com.google.protobuf.ByteString;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class test02 {
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
	public static void main(String[] args) {
		System.out.println("두번째 프로젝트");

	}

}
