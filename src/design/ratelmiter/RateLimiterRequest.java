package design.ratelmiter;

import java.util.BitSet;
import java.util.Objects;

public class RateLimiterRequest {
    private final String requestId;
    private final long startTime;

    public RateLimiterRequest(String requestId, long startTime) {
        this.requestId = requestId;
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateLimiterRequest that = (RateLimiterRequest) o;
        return startTime == that.startTime &&
                Objects.equals(requestId, that.requestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, startTime);
    }
}
