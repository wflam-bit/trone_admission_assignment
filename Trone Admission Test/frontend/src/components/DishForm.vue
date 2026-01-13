<template>
  <div class="bg-white rounded-2xl shadow p-6">
    <h2 class="text-2xl font-bold mb-4">{{ selected ? 'Edit Dish' : 'Add New Dish' }}</h2>
    <form @submit.prevent="submitForm" class="grid grid-cols-1 gap-4">
      <div>
        <label class="block text-gray-700">Name</label>
        <input v-model="localForm.name" class="w-full p-2 border rounded" required/>
        <p v-if="errors.name" class="text-red-500 text-sm mt-1">{{ errors.name }}</p>
      </div>
      <div>
        <label class="block text-gray-700">Country</label>
        <input v-model="localForm.country" class="w-full p-2 border rounded"/>
      </div>
      <div>
        <label class="block text-gray-700">Kind</label>
        <select v-model="localForm.kind" class="w-full p-2 border rounded">
          <option value="">Select kind</option>
          <option value="WARM">WARM</option>
          <option value="COLD">COLD</option>
          <option value="NORMAL">NORMAL</option>
        </select>
        <p v-if="errors.kind" class="text-red-500 text-sm mt-1">{{ errors.kind }}</p>
      </div>
      <div>
        <label class="block text-gray-700">Prep time</label>
        <input v-model.number="localForm.prep_time" class="w-full p-2 border rounded"/>
        <p v-if="errors.prep_time" class="text-red-500 text-sm mt-1">{{ errors.prep_time }}</p>
      </div>
      <div class="flex items-center gap-2">
        <label for="vegetarian" class="text-gray-700">Vegetarian</label>
        <input
            type="checkbox"
            id="vegetarian"
            v-model="localForm.vegetarian"
            class="w-4 h-4 text-indigo-600 border-gray-300 rounded"
        />
      </div>

      <div class="flex gap-4 mt-4">
        <button type="submit" class="px-6 py-3 bg-indigo-600 text-white rounded-xl hover:bg-indigo-700">
          {{ selected ? 'Update' : 'Create' }}
        </button>
        <button v-if="selected" type="button" @click="$emit('delete')"
                class="px-6 py-3 bg-red-600 text-white rounded-xl hover:bg-red-700">
          Delete
        </button>
        <button v-if="selected" type="button" @click="$emit('cancel')"
                class="px-6 py-3 bg-gray-300 rounded-xl hover:bg-gray-400">
          Cancel
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import {reactive, watch} from "vue";
// todo Maybe create Dish object?
export default {
  props: {
    selected: Object
  },
  emits: ["submit", "delete", "cancel"],
  setup(props, {emit}) {
    const emptyForm = () => ({
      id: null,
      name: "",
      country: "",
      kind: "",
      prep_time: 0,
      vegetarian: false,
      creation: null,
      update: null
    });

    const localForm = reactive(emptyForm());
    const errors = reactive({});
      // source: https://acv.engineering/posts/vue-computed-vs-watchers/
    watch(
        () => props.selected,
        val => {
          if (val) {
            Object.assign(localForm, {
              ...val,
              vegetarian: Boolean(val.vegetarian),  // Cast checkbox to boolean
              creation: val.creation ? new Date(val.creation) : null, // Create? Add timestamp
              update: val.update ? new Date(val.update) : null // Update/Modified data? Add timestamp
            });
          } else {
            Object.assign(localForm, emptyForm())
          }
        },
        {immediate: true}
    );


    // Will append to the errors array if true
      function validateForm() {
        errors.name = !localForm.name ? "Name is required" : "";
        errors.kind = !localForm.kind ? "Kind is required" : "";
        errors.prep_time = (!localForm.prep_time && localForm.prep_time !== 0)
            ? "Prep time is required"
            : localForm.prep_time < 0
                ? "Prep time cannot be negative"
                : "";

        // Return true if no errors
        return !errors.name && !errors.kind && !errors.prep_time;
      }


    function submitForm() {

        if(!validateForm()){
          return;
        }
      // On create Dish, attributes of creation or update need timestamp
      if (!props.selected) {
        localForm.creation = new Date();
      } else {
        localForm.update = new Date();
      }
      localForm.vegetarian = !!localForm.vegetarian;

      // Emit the submit event
      emit("submit", {...localForm});

      // Reset the form
      Object.assign(localForm, emptyForm());
    }
    return {localForm, errors, submitForm};
  }
};
</script>

<style>

</style>
