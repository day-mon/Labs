use std::env::args;
use std::error::Error;
use std::fs::{File, OpenOptions};
use csv::Writer;

const GENE_COUNT: usize = 10_000;

// take in args
fn main() {
    let n = args().nth(1).expect("No argument given").parse::<u32>().expect("Argument must be a number");
    let pc = args().nth(2).expect("No argument given").parse::<f32>().expect("Argument must be a number");
    let pm = args().nth(3).expect("No argument given").parse::<f32>().expect("Argument must be a number");

    let file_name = format!("{}.csv", rand::random::<i128>().to_string());
    let _ = File::create(&file_name).unwrap();
    let file: File = OpenOptions::new()
        .write(true)
        .append(true)
        .open(&file_name)
        .unwrap();


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


            let mut random = rand::random::<f32>();
            if random < pc {
                let (child_one, child_two) = crossover(&parent_one, &parent_two);
                parent_one = child_one;
                parent_two = child_two
            }

            random = rand::random::<f32>();
            if random < pm {
                mutate(&mut parent_one);
            }


            random = rand::random::<f32>();    
            if random < pm {
                mutate(&mut parent_two);
            }

            new_population.push(parent_one);
            new_population.push(parent_two);
        }


        population.clear();
        population.append(&mut new_population);

        if generation % 10 == 0 {
            let (avg, min, max) = get_population_stats(&population);
            println!("Generation {} | Average Fitness {} | Min Fitness {} | Max Fitness {}", generation, avg, min, max);
        }
        let _ = write_to_file(&population, generation, &file);
        generation += 1
    }
   // print how long it took
    let elapsed = start.elapsed();
    print_highest_fitness(&population);
    println!("generation: {}", generation);
    println!("\nTime to reach desired fitness: {:?}, Avg time per generation {:?}", elapsed, (elapsed / generation));}

fn mutate(chromosome: &mut Vec<u32>) -> &Vec<u32>
{
    let index = rand::random::<usize>() % GENE_COUNT;
    let value = chromosome[index];
    chromosome[index] = if value == 0 { 1 } else { 0 };
    chromosome
}

fn write_to_file(population: &Vec<Vec<u32>>, generation: u32, file: &File) -> Result<(), Box<dyn Error>> {
    let mut writer = Writer::from_writer(file);
    let (avg, min, max) = get_population_stats(population);
    writer.write_record(&[generation.to_string(), avg.to_string(), min.to_string(), max.to_string()])?;
    writer.flush()?;
    Ok(())
}

fn print_highest_fitness(population: &Vec<Vec<u32>>)
{
    let mut highest_fitness = 0;
    let mut highest_fitness_chromosome: Vec<u32> = Vec::new();
    for chromosome in population
    {
        let fitness = measure_fitness(chromosome);
        if fitness > highest_fitness
        {
            highest_fitness = fitness;
            highest_fitness_chromosome = chromosome.clone();
        }
    }
    println!("Highest Fitness: {}", highest_fitness);
    println!("Highest Fitness Chromosome: {:?}", highest_fitness_chromosome.iter().map(|x| x.to_string()).collect::<Vec<String>>().join(", "));
}


fn crossover(parent_one: &Vec<u32>, parent_two: &Vec<u32>) -> (Vec<u32>, Vec<u32>) {
    let crossover_point = rand::random::<usize>() % GENE_COUNT;

    let mut child_one_one = parent_one[0..crossover_point].to_vec();
    let mut child_one_two = parent_two[crossover_point..=GENE_COUNT -1].to_vec();

    let mut child_two_one = parent_one[crossover_point..=GENE_COUNT-1].to_vec();
    let mut child_two_two = parent_two[0..crossover_point].to_vec();

    child_two_one.append(&mut child_two_two);
    child_one_one.append(&mut child_one_two);


    return (child_one_one, child_two_one);

}

fn select_parents(population: &Vec<Vec<u32>>) -> Vec<u32>
{
    let mut best = vec![];
    for _ in 0..5
    {
        let index = rand::random::<usize>() % population.len();
        if measure_fitness(&population[index]) > measure_fitness(&best) {
            best = population[index].clone()
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
    if chromosome.len() == 0 { return 0 }
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


// get population statistics
fn get_population_stats(population: &Vec<Vec<u32>>) -> (f64, i64, i64) {
    let mut sum = 0 as f64;
    let mut min = 10000 as i64;
    let mut max = 0 as i64;
    for i in 0..population.len()
    {
        let fitness = measure_fitness(&population[i]) as i64;
        sum += fitness as f64;
        if fitness < min { min = fitness; }
        if fitness > max { max = fitness; }
    }
    let avg = sum / population.len() as f64;
    (avg, min, max)
}
