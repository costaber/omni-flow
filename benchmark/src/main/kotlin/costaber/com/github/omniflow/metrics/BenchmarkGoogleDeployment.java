package costaber.com.github.omniflow.metrics;

import costaber.com.github.omniflow.cloud.provider.google.renderer.GoogleTermContext;
import costaber.com.github.omniflow.cloud.provider.google.service.GoogleWorkflowService;
import costaber.com.github.omniflow.generator.WorkflowGenerator;
import costaber.com.github.omniflow.model.Workflow;
import costaber.com.github.omniflow.provider.OfficialWorkflowSamplesProvider;
import costaber.com.github.omniflow.provider.StrategyDeciderProvider;
import costaber.com.github.omniflow.renderer.IndentedRenderingContext;
import costaber.com.github.omniflow.traversor.DepthFirstNodeVisitorTraversor;
import costaber.com.github.omniflow.util.ListUtils;
import costaber.com.github.omniflow.visitor.NodeContextVisitor;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import java.io.IOException;
import java.util.HashMap;

import static costaber.com.github.omniflow.util.Constants.*;

public class BenchmarkGoogleDeployment extends BenchmarkWorkflowDeployment {

    private static final String PROJECT_ID = System.getenv(GOOGLE_PROJECT_ID_ENV_VAR);
    private static final String ZONE = System.getenv(GOOGLE_ZONE_ENV_VAR);
    private static final String SERVICE_ACCOUNT = System.getenv(GOOGLE_SERVICE_ACCOUNT_ENV_VAR);
    private static final HashMap<String, String> LABELS = new HashMap<String, String>() {{
        put(ORIGIN_LABEL, OMNI_FLOW_NAME);
        put(CLOUD_LABEL, GOOGLE);
        put(ENV_STR, ENVIRONMENT);
    }};

    private GoogleWorkflowService googleWorkflowService;

    @Setup
    public void setup() throws IOException {
        DepthFirstNodeVisitorTraversor traversor = new DepthFirstNodeVisitorTraversor();
        NodeContextVisitor googleContextVisitor = new NodeContextVisitor(
                StrategyDeciderProvider.googleNodeRendererStrategyDecider()
        );
        IndentedRenderingContext googleRenderingContext = new IndentedRenderingContext(
                0, new StringBuilder(), new GoogleTermContext()
        );
        Workflow workflow = WorkflowGenerator.textTranslator();
        generatedWorkflow = ListUtils.collectListString(
                traversor.traverse(
                        googleContextVisitor,
                        workflow,
                        googleRenderingContext
                )
        );
        exampleWorkflow = OfficialWorkflowSamplesProvider.google();
        googleWorkflowService = new GoogleWorkflowService();
    }

    @Benchmark
    @Override
    public void benchmarkGeneratedWorkflowDeployment() {
        googleWorkflowService.deploy(
                PROJECT_ID,
                ZONE,
                SERVICE_ACCOUNT,
                GENERATED_WORKFLOW_NAME,
                GOOGLE_GENERATED_WORKFLOW_DESC,
                LABELS,
                generatedWorkflow
        );
    }

    @Benchmark
    @Override
    public void benchmarkExampleWorkflowDeployment() {
        googleWorkflowService.deploy(
                PROJECT_ID,
                ZONE,
                SERVICE_ACCOUNT,
                EXAMPLE_WORKFLOW_NAME,
                GOOGLE_EXAMPLE_WORKFLOW_DESC,
                LABELS,
                exampleWorkflow
        );
    }
}
