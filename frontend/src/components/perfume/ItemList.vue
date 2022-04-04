<template>
  <v-card :loading="loading" class="mx-auto my-12" max-width="374">
    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      ></v-progress-linear>
    </template>

    <v-img
      height="250"
      src="https://www.jomalone.co.kr/media/export/cms/products/1000x1000/jo_sku_L41501_1000x1000_0.png"
    ></v-img>

    <v-card-title>Wood Sage & Sea Salt Cologne</v-card-title>

    <v-card-text>
      <v-row align="center" class="mx-0">
        <v-rating
          :value="4.5"
          color="amber"
          dense
          half-increments
          readonly
          size="14"
        ></v-rating>

        <div class="grey--text ms-4">4.5 (413)</div>
      </v-row>

      <div class="my-4 text-subtitle-1">Male, Jo Malone</div>

      <div>
        바람부는 해안을 따라 걸으며 일상을 벗어나보세요. 하얗게 부서지는 파도,
        소금기를 머금은 신선한 바다 공기.
      </div>
    </v-card-text>

    <v-divider class="mx-4"></v-divider>

    <v-card-actions>
      <v-btn color="deep-purple lighten-2" text @click="reserve">
        상품 보러가기
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: "item-list",
  data: () => ({
    loading: false,
    selection: 1,
  }),
  methods: {
    reserve() {
      this.loading = true;
      setTimeout(() => (this.loading = false), 2000);
    },
  },
};
</script>
