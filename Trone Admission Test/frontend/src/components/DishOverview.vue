<template>
  <div class="min-h-screen bg-gray-50 p-6">
    <div class="max-w-7xl mx-auto grid grid-cols-1 lg:grid-cols-2 gap-6">

      <!-- DISH LIST -->
      <div>
        <h1 class="text-3xl font-bold mb-6">Dishes 🍽</h1>
        <div class="grid grid-cols-1 gap-4">
          <div v-if="!dishes.length">
            <h2 class="text-2xl font-bold mb-4"></h2>
            Time to add new recipes
          </div>
          <div v-else
              v-for="dish in dishes"
              :key="dish.id"
              class="bg-white rounded-2xl shadow hover:shadow-lg cursor-pointer p-4"
              @click="selectDish(dish)"
          >
            <h2 class="text-lg font-semibold">{{ dish.name }}</h2>
            <p class="text-sm text-gray-600">Origin: {{ dish.country }}</p>
            <p class="text-sm text-gray-600">Kind: {{ dish.kind }}</p>
            <p class="text-sm text-gray-600">Prep time: {{ dish.prep_time }}</p>
            <p class="text-sm text-gray-600">Vegetarian: {{ dish.vegetarian }}</p>
          </div>

        </div>
      </div>

      <!-- DISH FORM -->
      <DishForm
          :selected="selected"
          @submit="handleSubmit"
          @delete="handleDelete"
          @cancel="resetForm"
      />

    </div>
  </div>
</template>

<script>
import DishForm from "@/components/DishForm.vue";
import DishDataService from "@/services/DishDataService.js";

export default {
  components: {DishForm},
  data() {
    return {
      dishes: [],
      selected: null
    };
  },
  methods: {
    retrieveDishes() {
      DishDataService.getAll()
          .then(res => (this.dishes = res.data))
          .catch(e => console.error(e));
    },
    selectDish(dish) {
      this.selected = dish;
      console.dir(dish)
    },
    handleSubmit(formData) {
      console.log(formData)
      if (this.selected) {
        DishDataService.update(this.selected.id, formData)
            .then(res => {
              console.log(res.data);
              this.resetForm();
              this.retrieveDishes()
            })
            .catch(e => {
              console.error(e);
            })
      } else {
        DishDataService.create(formData)
            .then(res => {
              console.log(res.data);
              this.resetForm();
              this.retrieveDishes()
            })
            .catch(e => {
              console.error(e)
            })
      }
    },

    handleDelete() {
      DishDataService.delete(this.selected.id)
          .then(response => {
            console.log(response.data);
            this.retrieveDishes()
          })
          .catch(e => {
            console.error(e)
          })
      this.resetForm();
    },
    resetForm() {
      this.selected = null;
    }
  },
  mounted() {
    this.retrieveDishes();
  }
};
</script>

<style scoped>
</style>
