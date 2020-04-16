#import "Dp3t.h"


@implementation Dp3t

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(sampleMethod:(NSString *)stringArgument numberParameter:(nonnull NSNumber *)numberArgument callback:(RCTResponseSenderBlock)callback)
{
    // TODO: Implement some actually useful functionality
    callback(@[[NSString stringWithFormat: @"numberArgument: %@ stringArgument: %@", numberArgument, stringArgument]]);
}

RCT_EXPORT_METHOD(startScan) {
    NSLog(@"TODO startScan");
}

RCT_EXPORT_METHOD(startAdvertising) {
    NSLog(@"TODO startAdvertising");
}

RCT_EXPORT_METHOD(startScanningAndAdvertising) {
    NSLog(@"TODO startScanningAndAdvertising");
}

RCT_EXPORT_METHOD(stop) {
    NSLog(@"TODO stop");
}

RCT_EXPORT_METHOD(sync) {
    NSLog(@"TODO sync");
}

RCT_EXPORT_METHOD(getScanInterval) {
    NSLog(@"TODO getScanInterval");
}

RCT_EXPORT_METHOD(getHandshakes) {
    NSLog(@"TODO getHandshakes");
}

RCT_EXPORT_METHOD(getStatus:(id)callback) {
    NSLog(@"TODO getStatus ");
}

@end
