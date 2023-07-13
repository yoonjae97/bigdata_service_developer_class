import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DDAplication {

    public static void main(String[] args) {
        String filePath = "C:\\prac\\KakaoTalk_20230713_002239702.jpg";
        try {
            detectHandwrittenOcr(filePath, System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void detectHandwrittenOcr(String filePath, PrintStream out) throws Exception {
        List<AnnotateImageRequest> requests = new ArrayList<>();

        byte[] imgBytes = Files.readAllBytes(Paths.get(filePath));

        ByteString imgData = ByteString.copyFrom(imgBytes);

        Image img = Image.newBuilder().setContent(imgData).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.DOCUMENT_TEXT_DETECTION).build();
        // Set the Language Hint codes for handwritten OCR
        ImageContext imageContext = ImageContext.newBuilder().addLanguageHints("en-t-i0-handwrit").build();

        AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img)
                .setImageContext(imageContext).build();
        requests.add(request);

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

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
                                    out.format("Symbol text: %s (confidence: %f)\n", symbol.getText(),
                                            symbol.getConfidence());
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
}
