package costaber.com.github.omniflow.provider;

import costaber.com.github.omniflow.cloud.provider.amazon.strategy.*;
import costaber.com.github.omniflow.cloud.provider.google.strategy.*;
import costaber.com.github.omniflow.factory.DefaultNodeRendererStrategyDecider;
import costaber.com.github.omniflow.factory.NodeRendererStrategyDecider;

public class StrategyDeciderProvider {

    public static NodeRendererStrategyDecider googleNodeRendererStrategyDecider() {
        return new DefaultNodeRendererStrategyDecider.Builder()
                .addRendererStrategy(new GoogleAssignStrategyFactory())
                .addRendererStrategy(new GoogleCallStrategyFactory())
                .addRendererStrategy(new GoogleConditionStrategyFactory())
                .addRendererStrategy(new GoogleEqualToExpressionStrategyFactory())
                .addRendererStrategy(new GoogleGreaterThanExpressionStrategyFactory())
                .addRendererStrategy(new GoogleGreaterThanOrEqualExpressionStrategyFactory())
                .addRendererStrategy(new GoogleLessThanExpressionStrategyFactory())
                .addRendererStrategy(new GoogleLessThanOrEqualExpressionStrategyFactory())
                .addRendererStrategy(new GoogleNotEqualToExpressionStrategyFactory())
                .addRendererStrategy(new GoogleStepStrategyFactory())
                .addRendererStrategy(new GoogleSwitchStrategyFactory())
                .addRendererStrategy(new GoogleVariableStrategyFactory())
                .addRendererStrategy(new GoogleWorkflowStrategyFactory())
                .build();
    }

    public static NodeRendererStrategyDecider amazonNodeRendererStrategyDecider() {
        return new DefaultNodeRendererStrategyDecider.Builder()
                .addRendererStrategy(new AmazonChoiceStrategyFactory())
                .addRendererStrategy(new AmazonConditionStrategyFactory())
                .addRendererStrategy(new AmazonEqualToExpressionStrategyFactory())
                .addRendererStrategy(new AmazonGreaterThanExpressionStrategyFactory())
                .addRendererStrategy(new AmazonGreaterThanOrEqualExpressionStrategyFactory())
                .addRendererStrategy(new AmazonLessThanExpressionStrategyFactory())
                .addRendererStrategy(new AmazonLessThanOrEqualExpressionStrategyFactory())
                .addRendererStrategy(new AmazonNotEqualToExpressionStrategyFactory())
                .addRendererStrategy(new AmazonPassStrategyFactory())
                .addRendererStrategy(new AmazonStateMachineStrategyFactory())
                .addRendererStrategy(new AmazonStateStrategyFactory())
                .addRendererStrategy(new AmazonTaskStrategyFactory())
                .addRendererStrategy(new AmazonVariableStrategyFactory())
                .build();
    }
}