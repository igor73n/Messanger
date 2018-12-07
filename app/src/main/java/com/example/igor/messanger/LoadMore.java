package com.example.igor.messanger;

public class LoadMore extends ListItem {

        private String loadMore;

        public String getLoadMore() {
            return loadMore;
        }

        public void setLoadMore(String loadMore) {
            this.loadMore = loadMore;
        }

        @Override
        public int getType() {
            return TYPE_LOAD_MORE;
        }

}
