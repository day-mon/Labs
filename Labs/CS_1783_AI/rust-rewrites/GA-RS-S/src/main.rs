
const GENE_COUNT: usize = 8000;

fn main() {
    let n = 1000;
    let pc = 0.5;
    let pm = 0.05;


    let start = std::time::Instant::now();
    let mut population = init_population(n);
    let mut new_population: Vec<Vec<u32>> = Vec::new();
    let mut generation = 1;

    while get_best_fitness(&population) != GENE_COUNT as usize
    {
        for _ in 0..n / 2
        {
            let mut parent_one = select_parents(&population);
            let mut parent_two = select_parents(&population);

            let mut random = rand::random::<f64>();
            if random < pc {
                let children = crossover(&parent_one, &parent_two);
                parent_one = children.0;
                parent_two = children.1;
            }

            random = rand::random::<f64>();
            if random < pm {
                mutate(&mut parent_one);
            }


            random = rand::random::<f64>();
            if random < pm {
                mutate(&mut parent_two);
            }

            new_population.push(parent_one);
            new_population.push(parent_two);
        }

        if generation % 10 == 0 {
            println!("Generation {} | Highest Fitness {}", generation, get_best_fitness(&population))
        }
        population.clear();
        population.append(&mut new_population);
        generation += 1;
    }
    // print how long it took
    let elapsed = start.elapsed();
    println!("generation: {}", generation);
    println!("\nTime to reach desired fitness: {:?}, Avg time per generation {:?}", elapsed, (elapsed / generation));}

fn mutate(chromosome: &mut Vec<u32>) -> &Vec<u32>
{
    let index = rand::random::<usize>() % GENE_COUNT;
    let value = chromosome[index];
    chromosome[index] = if value == 0 { 1 } else { 0 };
    return chromosome;
}



fn crossover(parent_one: &Vec<u32>, parent_two: &Vec<u32>) -> (Vec<u32>, Vec<u32>) {
    let crossover_point = rand::random::<usize>() % GENE_COUNT;

    let mut child_one_one = parent_one[0..crossover_point].to_vec();
    let mut child_one_two = parent_one[crossover_point..=GENE_COUNT -1].to_vec();

    let mut child_two_one = parent_two[0..crossover_point].to_vec();
    let mut child_two_two = parent_two[crossover_point..=GENE_COUNT -1].to_vec();

    child_two_one.append(&mut child_two_two);
    child_one_one.append(&mut child_one_two);


    return (child_one_one, child_two_one);

}

fn select_parents(population: &Vec<Vec<u32>>) -> Vec<u32>
{
    let mut tournament = Vec::new();
    for _ in 0..5
    {
        let index = rand::random::<usize>() % population.len();
        tournament.push(population[index].clone());
    }

    let mut best = tournament[0].clone();
    for i in 0..tournament.len()
    {
        if measure_fitness(&tournament[i]) > measure_fitness(&best)
        {
            best = tournament[i].clone();
        }
    }

    best
}


fn get_best_fitness(population: &Vec<Vec<u32>>) -> usize {
    let mut best = 0;
    for i in 0..population.len()
    {
        let fitness = measure_fitness(&population[i]);
        if fitness > best
        {
            best = fitness;
        }
    }
    best
}

fn measure_fitness(chromosome: &Vec<u32>) -> usize {
    let mut fitness = 0;
    for i in 0..GENE_COUNT {
        fitness += chromosome[i] as usize;
    }
    return fitness;
}

fn init_population(n: u32) -> Vec<Vec<u32>> {

    let mut population:Vec<Vec<u32>> = Vec::new();
    for _ in 0..n
    {
        let mut chromosome: Vec<u32> = Vec::with_capacity(GENE_COUNT as usize);
        for _ in 0..GENE_COUNT
        {
            let num = rand::random::<usize>() % 2;
            chromosome.push(num as u32);
        }
        population.push(chromosome)
    }
    return population
}