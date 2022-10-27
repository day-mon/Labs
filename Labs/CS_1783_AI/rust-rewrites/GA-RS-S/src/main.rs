use rand::{Rng, thread_rng};

const GENE_COUNT: usize = 100;

fn main() {
    let n = 100;
    let pc = 0.5;
    let pm = 0.05;


    let mut population = init_population(n);
    let mut new_population: Vec<Vec<u32>> = Vec::new();
    let mut random = thread_rng();
    let mut generation = 1;

    let start = std::time::Instant::now();

    while get_best_fitness(&population) != GENE_COUNT as usize
    {
        for _ in 0 .. n / 2
        {
            let parents = select_parents(&population);
            let mut parent_one = parents.0;
            let mut parent_two = parents.1;

            if random.gen_range(0.00..1.00) < pc {
                let children = crossover(&parent_one, &parent_two);
                parent_one = children.0;
                parent_two = children.1;
            }

            if random.gen_range(0.00..1.00) < pm {
                mutate(&mut parent_one);
            }


            if random.gen_range(0.00..1.00) < pm {
                mutate(&mut parent_two);
            }

            new_population.push(parent_one);
            new_population.push(parent_two);
        }
        // if generation % 10 == 0 {
        //     println!("Generation {} | Highest Fitness {}", generation, get_best_fitness(&population))
        // }
        population.clear();
        population.append(&mut new_population);
        generation += 1;
    }
   // print how long it took
    println!("Time elapsed: {}ms | Highest Fitness {}", start.elapsed().as_millis(), get_best_fitness(&population));
}

fn mutate(chromosome: &mut Vec<u32>) -> &Vec<u32>
{
    let mut random = thread_rng();
    let index = random.gen_range(0..GENE_COUNT);
    let value = chromosome[index];
    chromosome[index] = if value == 0 { 1 } else { 0 };
    return chromosome;
}



fn crossover(parent_one: &Vec<u32>, parent_two: &Vec<u32>) -> (Vec<u32>, Vec<u32>) {
    let mut random = thread_rng();
    let crossover_point = random.gen_range(0..GENE_COUNT - 1) as usize;

    let mut child_one_one = parent_one[0..crossover_point].to_vec();
    let mut child_one_two = parent_one[crossover_point..=GENE_COUNT -1].to_vec();

    let mut child_two_one = parent_two[0..crossover_point].to_vec();
    let mut child_two_two = parent_two[crossover_point..=GENE_COUNT -1].to_vec();

    child_two_one.append(&mut child_two_two);
    child_one_one.append(&mut child_one_two);


    return (child_one_one, child_two_one);

}

fn select_parents(population: &Vec<Vec<u32>>) -> (Vec<u32>, Vec<u32>) {
    // we are going to do tournment selection here
    let parent_pool_size = 2;
    let mut parent_pool: Vec<(Vec<u32>, Vec<u32>)> = Vec::with_capacity(parent_pool_size);
    let mut rand = rand::thread_rng();


    for _ in 0..parent_pool_size
    {
        let mut parent_pool_sub: Vec<Vec<u32>> = Vec::with_capacity(2);
        while parent_pool_sub.len() != 2
        {
            let parent_one = population[rand.gen_range(0..population.len())].clone();
            let parent_two = population[rand.gen_range(0..population.len())].clone();
            let better_parent = if measure_fitness(&parent_two) > measure_fitness(&parent_one) {
                parent_two
            } else {
                parent_one
            };
            parent_pool_sub.push(better_parent);
        }
        parent_pool.push((parent_pool_sub[0].clone(), parent_pool_sub[1].clone()))

    }

    let mut best = parent_pool[0].clone();
    for parents in parent_pool {
        if measure_fitness(&parents.1) > measure_fitness(&best.1)
            &&  measure_fitness(&parents.0) > measure_fitness(&best.1) {
            best = parents
        }
    }

    return best;


}

fn get_best_fitness(population: &Vec<Vec<u32>>) -> usize {
     population.iter().map(|c| measure_fitness(c)).max().unwrap()
}

fn measure_fitness(chromosome: &Vec<u32>) -> usize {
    chromosome.iter().filter(|&n| *n == 1).count()
}

fn init_population(n: u32) -> Vec<Vec<u32>> {

    let mut population:Vec<Vec<u32>> = Vec::new();
    let mut rand = thread_rng();
    for _ in 0..n
    {
        let mut chromosome: Vec<u32> = Vec::with_capacity(GENE_COUNT as usize);
        for _ in 0..GENE_COUNT
        {
            let num = rand.gen_range(0..2);
            chromosome.push(num);
        }
        population.push(chromosome)
    }
    return population
}