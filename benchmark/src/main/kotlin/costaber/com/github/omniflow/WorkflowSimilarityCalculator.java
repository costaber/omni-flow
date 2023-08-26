package costaber.com.github.omniflow;

import costaber.com.github.omniflow.cloud.provider.amazon.renderer.AmazonRenderingContext;
import costaber.com.github.omniflow.cloud.provider.google.renderer.GoogleTermContext;
import costaber.com.github.omniflow.factory.NodeRendererStrategyDecider;
import costaber.com.github.omniflow.generator.WorkflowGenerator;
import costaber.com.github.omniflow.model.Workflow;
import costaber.com.github.omniflow.provider.OfficialWorkflowSamplesProvider;
import costaber.com.github.omniflow.provider.StrategyDeciderProvider;
import costaber.com.github.omniflow.renderer.IndentedRenderingContext;
import costaber.com.github.omniflow.traversor.DepthFirstNodeVisitorTraversor;
import costaber.com.github.omniflow.util.ListUtils;
import costaber.com.github.omniflow.visitor.NodeContextVisitor;

import java.io.IOException;
import java.text.DecimalFormat;

public class WorkflowSimilarityCalculator {

    public static void main(String[] args) throws IOException {
        DecimalFormat decimalFormat = new DecimalFormat("#.###");

        double amazonWorkflowDifference = calculateDifferenceForCloudProvider(
                StrategyDeciderProvider.amazonNodeRendererStrategyDecider(),
                new AmazonRenderingContext(),
                WorkflowGenerator.saveAndGetPetFromStore(),
                OfficialWorkflowSamplesProvider.amazon()
        );

        String amazonSimilarityPercentage = decimalFormat.format(amazonWorkflowDifference);
        System.out.println("Amazon Similarity: " + amazonSimilarityPercentage + " %");

        double googleWorkflowDifference = calculateDifferenceForCloudProvider(
                StrategyDeciderProvider.googleNodeRendererStrategyDecider(),
                new IndentedRenderingContext(0, new StringBuilder(), new GoogleTermContext()),
                WorkflowGenerator.textTranslator(),
                OfficialWorkflowSamplesProvider.google()
        );

        String googleSimilarityPercentage = decimalFormat.format(googleWorkflowDifference);
        System.out.println("Google Similarity: " + googleSimilarityPercentage + " %");
    }

    private static double calculateDifferenceForCloudProvider(
            NodeRendererStrategyDecider nodeRendererStrategyDecider,
            IndentedRenderingContext indentedRenderingContext,
            Workflow generatedWorkflow,
            String exampleWorkflow
    ) {
        DepthFirstNodeVisitorTraversor traversor = new DepthFirstNodeVisitorTraversor();
        NodeContextVisitor contextVisitor = new NodeContextVisitor(nodeRendererStrategyDecider);
        String generatedWorkflowRendered = ListUtils.collectListString(
                traversor.traverse(contextVisitor, generatedWorkflow, indentedRenderingContext)
        );
        return calculateDifferencePercentage(generatedWorkflowRendered, exampleWorkflow);
    }

    private static double calculateDifferencePercentage(String content1, String content2) {
        String[] content3 = content1.split(System.lineSeparator());
        String[] content4 = content2.split(System.lineSeparator());

        String[] smallerContent = content3;
        if (content4.length < content3.length) {
            smallerContent = content4;
        }

        int differences = Math.abs(content3.length - content4.length);

        for (int i = 0; i < smallerContent.length; i++) {
            if (!content3[i].equals(content4[i])) {
                differences++;
            }
        }

        double maxNumberDiff = Math.max(content3.length, content4.length);

        return (differences / maxNumberDiff) * 100;
    }
}