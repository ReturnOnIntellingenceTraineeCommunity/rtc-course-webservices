package net.github.rtc.micro.course.model.dto;

/**
 * @author Vladislav Pikus
 */
public final class PageDto {
    private int firstResult;
    private int maxResult;

    private void initialize(int page, int maxResult) {
        this.maxResult = (maxResult < 0) ? 1 : maxResult;
        this.firstResult = page * this.maxResult;
    }

    public PageDto(int firstResult, int maxResult) {
        this.firstResult = firstResult;
        this.maxResult = maxResult;
    }

    public PageDto(Builder builder) {
        initialize(builder.page, (builder.maxResult <= 0) ? builder.total : builder.maxResult);
    }

    public int getFirstResult() {
        return firstResult;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public static class Builder {
        private int page;
        private int maxResult = 10;
        private final int total;

        public Builder(int total) {
            this.total = total;
        }

        public Builder page(int val) {
            page = val;
            return this;
        }

        public Builder maxResult(int val) {
            maxResult = val;
            return this;
        }

        public PageDto build() {
            return new PageDto(this);
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageDto{");
        sb.append("firstResult=").append(firstResult);
        sb.append(", maxResult=").append(maxResult);
        sb.append('}');
        return sb.toString();
    }
}
