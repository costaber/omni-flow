package costaber.com.github.omniflow.metrics;

import costaber.com.github.omniflow.cloud.provider.amazon.renderer.AmazonRenderingContext;
import costaber.com.github.omniflow.cloud.provider.amazon.service.AmazonStateMachineService;
import costaber.com.github.omniflow.generator.WorkflowGenerator;
import costaber.com.github.omniflow.model.Workflow;
import costaber.com.github.omniflow.provider.OfficialWorkflowSamplesProvider;
import costaber.com.github.omniflow.provider.StrategyDeciderProvider;
import costaber.com.github.omniflow.traversor.DepthFirstNodeVisitorTraversor;
import costaber.com.github.omniflow.util.ListUtils;
import costaber.com.github.omniflow.visitor.NodeContextVisitor;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import java.io.IOException;
import java.util.HashMap;

import static costaber.com.github.omniflow.util.Constants.*;

public class BenchmarkAmazonDeployment extends BenchmarkWorkflowDeployment {

    private static final String ARN = System.getenv(AWS_ROLE_ARN_ENV_VAR);
    private static final String REGION = System.getenv(AWS_REGION_ENV_VAR);

    private AmazonStateMachineService amazonStateMachineService;
    private final HashMap<String, String> tags = new HashMap<String, String>() {{
        put(ORIGIN_LABEL, OMNI_FLOW_NAME);
        put(CLOUD_LABEL, AMAZON);
        put(ENV_STR, ENVIRONMENT);
    }};

    @Setup
    public void setup() throws IOException {
        DepthFirstNodeVisitorTraversor traversor = new DepthFirstNodeVisitorTraversor();
        NodeContextVisitor amazonContextVisitor = new NodeContextVisitor(
                StrategyDeciderProvider.amazonNodeRendererStrategyDecider()
        );
        AmazonRenderingContext amazonRenderingContext = new AmazonRenderingContext();
        Workflow saveAndGetPetFromStoreWorkflow = WorkflowGenerator.saveAndGetPetFromStore();
        generatedWorkflow = ListUtils.collectListString(
                traversor.traverse(
                        amazonContextVisitor,
                        saveAndGetPetFromStoreWorkflow,
                        amazonRenderingContext
                )
        );
        exampleWorkflow = OfficialWorkflowSamplesProvider.amazon();
        amazonStateMachineService = new AmazonStateMachineService();
    }

    @Benchmark
    @Override
    public void benchmarkGeneratedWorkflowDeployment() {
        amazonStateMachineService.createStateMachine(
                ARN,
                REGION,
                tags,
                GENERATED_WORKFLOW_NAME,
                generatedWorkflow
        );
    }

    @Benchmark
    @Override
    public void benchmarkExampleWorkflowDeployment() {
        amazonStateMachineService.createStateMachine(
                ARN,
                REGION,
                tags,
                EXAMPLE_WORKFLOW_NAME,
                exampleWorkflow
        );
    }
}
